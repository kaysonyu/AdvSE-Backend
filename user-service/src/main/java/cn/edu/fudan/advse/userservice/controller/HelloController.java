package cn.edu.fudan.advse.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public Mono<String> student() {
        return Mono.just("Hello, student!");
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public Mono<String> teacher() {
        return Mono.just("Hello, teacher!");
    }

}
