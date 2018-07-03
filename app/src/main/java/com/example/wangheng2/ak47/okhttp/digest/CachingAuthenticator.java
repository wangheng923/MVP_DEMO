package com.example.wangheng2.ak47.okhttp.digest;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Route;

import java.io.IOException;

/**
 * A subinterface for authenticators which support auth sessions.
 */
public interface CachingAuthenticator extends Authenticator {
    /**
     * Authenticate the new request using cached information already established from an earlier
     * authentication.
     *
     * @param route   the route to use
     * @param request the new request to be authenticated.
     * @return the modified request with updated auth headers.
     * @throws IOException in case of a communication problem
     */
    Request authenticateWithState(Route route, Request request) throws IOException;
}
