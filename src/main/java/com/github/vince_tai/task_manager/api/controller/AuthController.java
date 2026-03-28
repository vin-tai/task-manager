package com.github.vince_tai.task_manager.api.controller;

import com.github.vince_tai.task_manager.api.dto.TokenResponse;
import com.github.vince_tai.task_manager.security.AccountAdapter;
import com.github.vince_tai.task_manager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/token")
    public ResponseEntity<TokenResponse> token(@AuthenticationPrincipal AccountAdapter accountAdapter) {
        List<String> authorities = accountAdapter.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        String username = accountAdapter.getUsername();
        TokenResponse response = authService.createToken(username, authorities);
        return ResponseEntity.ok().body(response);
    }
}
