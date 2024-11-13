package cn.edu.fudan.advse.userservice.controller;

import cn.edu.fudan.advse.userservice.entity.User;
import cn.edu.fudan.advse.userservice.request.LoginRequest;
import cn.edu.fudan.advse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Mono<Object> register(@RequestBody User user) {
        System.out.println("User in request: " + user);
        try {
            User newUser = userService.register(user);
            return Mono.just(newUser);
        } catch (Exception e) {
            return Mono.just(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Mono<Object> login(@RequestBody LoginRequest request) {
        try {
            System.out.println("enter login");
            String token = userService.login(request.getUsername(), request.getPassword());
            System.out.println("login success");
            // return token to frontend
            Map<String, String> resp = new HashMap<>();
            resp.put("token", token);
            return Mono.just(resp);
        } catch (Exception e) {
            return Mono.just(e.getMessage());
        }
    }

}
