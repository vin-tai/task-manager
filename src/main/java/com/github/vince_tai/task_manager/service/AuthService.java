package com.github.vince_tai.task_manager.service;

import com.github.vince_tai.task_manager.api.dto.TokenResponse;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AuthService {
    private final JwtEncoder jwtEncoder;

    public AuthService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }
    public TokenResponse createToken(String username, List<String> authorities) {
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(600, ChronoUnit.SECONDS))
                .claim("scope", authorities)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claimsSet))
                .getTokenValue();

        return new TokenResponse(token);
    }
}
