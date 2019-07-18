package com.kodascript.oauth2sosial.security.oauth2;

import com.kodascript.oauth2sosial.util.CookiesUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request_cookie_name";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    private static final int cookieExpiredSeconds = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest httpServletRequest) {
        return CookiesUtils.getCookie(httpServletRequest, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> CookiesUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest oAuth2AuthorizationRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (oAuth2AuthorizationRequest == null) {
            CookiesUtils.deleteCookie(httpServletRequest, httpServletResponse, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookiesUtils.deleteCookie(httpServletRequest, httpServletResponse, REDIRECT_URI_PARAM_COOKIE_NAME);
            return;
        }
        CookiesUtils.addCookie(httpServletResponse, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, CookiesUtils.serialize(oAuth2AuthorizationRequest), cookieExpiredSeconds);
        String redirectUriAfterLogin = httpServletRequest.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookiesUtils.addCookie(httpServletResponse, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, cookieExpiredSeconds);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookiesUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        CookiesUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
    }
}
