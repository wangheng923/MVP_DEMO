package com.example.wangheng2.ak47.util;


import com.example.wangheng2.ak47.okhttp.AuthenticationCacheInterceptor;
import com.example.wangheng2.ak47.okhttp.CachingAuthenticatorDecorator;
import com.example.wangheng2.ak47.okhttp.digest.CachingAuthenticator;
import com.example.wangheng2.ak47.okhttp.digest.Credentials;
import com.example.wangheng2.ak47.okhttp.digest.DigestAuthenticator;

import junit.framework.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wangheng2 on 2018/1/24.
 */

public final class HttpUtil {

    private static OkHttpClient client;
    private static Credentials credentials;
    private static Map<String, CachingAuthenticator> cache;

    public static void setCredentials(Credentials credentials) {
        HttpUtil.credentials = credentials;
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            initClient();
        }
        return client;
    }

    public static void initClient() {
        Assert.assertNotNull("无身份认证信息", credentials);
        if (cache == null) {
            cache = new HashMap<String, CachingAuthenticator>();
        }
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //addInterceptor(new AuthenticationCacheInterceptor(cache)).authenticator(new CachingAuthenticatorDecorator(new DigestAuthenticator(credentials), cache)).
        client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }
//
//    public static void request(String url, String method, Callback callback) throws IOException {
//        request(url, method, null, callback);
//    }
//
//    public static void request(String url, String method, RequestBody requestBody, Callback callback) throws IOException {
//        if (client == null) {
//            initClient();
//        }
//        Request.Builder builder = new Request.Builder().url(url);
//        method = method.toUpperCase();
//        if (requestBody == null) {
//            requestBody = new FormBody.Builder().build();
//        }
//        switch (method) {
//            case "POST":
//                builder.post(requestBody);
//                break;
//            case "PUT":
//                builder.put(requestBody);
//                break;
//            case "DELETE":
//                builder.delete(requestBody);
//                break;
//            case "GET":
//                break;
//            default:
//                break;
//        }
//        Request request = builder.build();
//        client.newCall(request).enqueue(callback);
//    }
}
