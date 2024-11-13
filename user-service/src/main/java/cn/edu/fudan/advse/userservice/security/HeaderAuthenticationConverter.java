package cn.edu.fudan.advse.userservice.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class HeaderAuthenticationConverter implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        String username = exchange.getRequest().getHeaders().getFirst("X-User-Name");
        String role = exchange.getRequest().getHeaders().getFirst("X-User-Role");
        System.out.println("==================DEBUG CONVERT==================");
        if (username != null && role != null) {
            System.out.println("Extracted username: " + username);
            System.out.println("Extracted role: " + role);
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(authority));
            return Mono.just(authentication);
        }
        System.out.println("Failed extract username and role, headers: " + exchange.getRequest().getHeaders());
        return Mono.empty();
    }
}
