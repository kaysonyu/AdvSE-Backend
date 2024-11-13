package cn.edu.fudan.advse.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class UserContextFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(securityContext -> {
                    Authentication authentication = securityContext.getAuthentication();
                    if (authentication != null && authentication.isAuthenticated()) {
                        // get username and role
                        String username = authentication.getName();
                        String role = authentication.getAuthorities().iterator().next().getAuthority();
                        System.out.println("======================DEBUG====================");
                        System.out.println(username);
                        System.out.println(role);
                        System.out.println("======================DEBUG====================");
                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                            @Override
                            public HttpHeaders getHeaders() {
                                HttpHeaders headers = new HttpHeaders();
                                headers.putAll(super.getHeaders());
                                // add User Details to headers
                                headers.put("X-User-Name", Collections.singletonList(username));
                                headers.put("X-User-Role", Collections.singletonList(role));
                                return headers;
                            }
                        };
                        // build a new exchange with the mutated request
                        ServerWebExchange mutatedExchange = exchange.mutate()
                                .request(mutatedRequest)
                                .build();
                        // continue filter chain with mutated exchange
                        return chain.filter(mutatedExchange);
                    } else {
                        // no authentication, continue with the original exchange
                        return chain.filter(exchange);
                    }
                })
                .switchIfEmpty(chain.filter(exchange)); // in case the context is empty
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
