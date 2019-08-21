package com.kodascript.oauth2sosial.security.oauth2.user;

import com.kodascript.oauth2sosial.exception.OAuth2AuthenticationProcessingException;
import com.kodascript.oauth2sosial.model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attribute) {
        if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attribute);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attribute);
        }else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with "+registrationId+"is not supported yet. ");
        }
    }
}
