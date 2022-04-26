package com.example.hellodemo.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/oauth")
public class RestAuthController {

    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize());
    }

    @RequestMapping("/callback")
    public AuthResponse login(String code, String state) {
        AuthRequest authRequest = getAuthRequest();
        AuthCallback authCallback = new AuthCallback();
        authCallback.setCode(code);
        authCallback.setState(state);
        return authRequest.login(authCallback);
    }

    @RequestMapping("/revoke/{token}")
    public Object revokeAuth(@PathVariable("token") String token) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        AuthToken authToken = new AuthToken();
        authToken.setOauthToken(token);
        return authRequest.revoke(authToken);
    }

    private AuthRequest getAuthRequest() {
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId("fc00419555a230e3465960e57ad804de191c4b3409980dcf227505f1960e62a8")
                .clientSecret("ff778c82fceb8e1dea82ab0f3f8c8bfbbc67c96d83df40c91b0548ec086ba0da")
                .redirectUri("http://127.0.0.1:8888/oauth/callback")
                .build());
    }
}
