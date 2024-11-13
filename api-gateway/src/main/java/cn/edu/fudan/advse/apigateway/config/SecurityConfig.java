package cn.edu.fudan.advse.apigateway.config;

import cn.edu.fudan.advse.apigateway.security.JwtAuthenticationManager;
import cn.edu.fudan.advse.apigateway.security.JwtServerAuthenticationConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Value("${security.white.paths}")
    private String[] whitePaths;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        /**
         * TODO: original code
         * http
         *             .csrf().disable()
         *             .authorizeExchange()
         *                 .pathMatchers(HttpMethod.OPTIONS).permitAll()
         *                 .pathMatchers(permitPaths).permitAll()
         *                 .anyExchange().authenticated()
         *             .and()
         *             .authenticationManager(authenticationManager)
         *             .httpBasic().disable()
         *             .formLogin().disable()
         *             .logout().disable()
         *             .addFilterAt(authenticationWebFilter(authenticationManager, authenticationConverter), SecurityWebFilterChain.AUTHENTICATION_FILTER_ORDER);
         */
        ReactiveAuthenticationManager authenticationManager = new JwtAuthenticationManager(secretKey);
        JwtServerAuthenticationConverter authenticationConverter = new JwtServerAuthenticationConverter();
        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                                .pathMatchers(whitePaths).permitAll()
                                .anyExchange().authenticated()
                )
                .authenticationManager(authenticationManager)
                .addFilterAt(authenticationWebFilter(authenticationManager, authenticationConverter), SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

    private AuthenticationWebFilter authenticationWebFilter(ReactiveAuthenticationManager authenticationManager, JwtServerAuthenticationConverter authenticationConverter) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(authenticationConverter);
        authenticationWebFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));
        return authenticationWebFilter;
    }

}
