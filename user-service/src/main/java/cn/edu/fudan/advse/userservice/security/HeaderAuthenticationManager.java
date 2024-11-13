package cn.edu.fudan.advse.userservice.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class HeaderAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        System.out.println("==================DEBUG AUTHENTICATION================");
        System.out.println("Authenticating user: " + authentication.getName());
        System.out.println("Authenticating authorities: " + authentication.getAuthorities());
        System.out.println("Authentication state: " + authentication.isAuthenticated());
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            authentication.setAuthenticated(true);
            // set security context
            return Mono.just(authentication);
        }
        return Mono.empty();
    }
}
