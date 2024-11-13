package cn.edu.fudan.advse.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final String secretKey;

    public JwtAuthenticationManager(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        try {
            // parse jwt token
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(authToken)
                    .getBody();
            // get username and role
            String username = claims.getSubject();
            String role = claims.get("role", String.class);
            // create authorities
            List<GrantedAuthority> authorities = Arrays.asList(
                    new SimpleGrantedAuthority("ROLE_" + role)
            );
            // create Authentication
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
            return Mono.just(auth);
        } catch (JwtException e) {
            return Mono.error(new BadCredentialsException("Invalid token"));
        }
    }
}
