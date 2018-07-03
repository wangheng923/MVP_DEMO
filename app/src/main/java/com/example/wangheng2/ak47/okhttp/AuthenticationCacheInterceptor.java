package com.example.wangheng2.ak47.okhttp;


import com.example.wangheng2.ak47.okhttp.digest.CachingAuthenticator;
import com.example.wangheng2.ak47.okhttp.digest.DigestAuthenticator;
import com.example.wangheng2.ak47.util.HttpUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.platform.Platform;

import static java.net.HttpURLConnection.HTTP_PROXY_AUTH;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

/**
 * An HTTP Request interceptor that adds previous auth headers in to the same host. This enables the
 * client to reduce the number of 401 auth request/response cycles.
 */
public class AuthenticationCacheInterceptor implements Interceptor {

    /**
     * HTTP Status-Code 420: Unauthorized.
     */
    public static final int HTTP_UNAUTHORIZED_LOCAL = 420;
    private final Map<String, CachingAuthenticator> authCache;

    public AuthenticationCacheInterceptor(Map<String, CachingAuthenticator> authCache) {
        this.authCache = authCache;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        System.out.println(this);
        final Request request = chain.request();
        final HttpUrl url = request.url();
        final String key = CachingUtils.getCachingKey(url);
        CachingAuthenticator authenticator = authCache.get(key);
        Request authRequest = null;
        Connection connection = chain.connection();
        Route route = connection != null ? connection.route() : null;

        if (authenticator != null) {
            authRequest = authenticator.authenticateWithState(route, request);
        }
        if (authRequest == null) {
            authRequest = request;
        }
        Response response = chain.proceed(authRequest);

        // Cached response was used, but it produced unauthorized response (cache expired).
        int responseCode = response != null ? response.code() : 0;
        if (authenticator != null && (responseCode == HTTP_UNAUTHORIZED || responseCode == HTTP_PROXY_AUTH)) {
            // Remove cached authenticator and resend request
            if (authCache.remove(key) != null) {
                response.body().close();
                Platform.get().log(Platform.INFO, "Cached authentication expired. Sending a new request.", null);
                // Force sending a new request without "Authorization" header
                response = chain.proceed(request);
            }
        }

        if (responseCode == HTTP_UNAUTHORIZED_LOCAL) {
            if (authenticator != null && authCache.remove(key) != null) {
                Platform.get().log(Platform.INFO, "Cached authentication expired. Sending a new request.", null);
            }
            response.body().close();
            authRequest = HttpUtil.getClient().authenticator().authenticate(route, response);
            if (authRequest == null) {
                authRequest = request;
            }
            response = chain.proceed(authRequest);
        }

        return response;
    }
}
