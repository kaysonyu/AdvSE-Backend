package cn.edu.fudan.advse.courseservice.controller;

import cn.edu.fudan.advse.commonlibrary.lib.UserRequest;
import cn.edu.fudan.advse.commonlibrary.lib.UserResponse;
import cn.edu.fudan.advse.commonlibrary.lib.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GetMapping("/user")
    public String hello() {
        UserResponse user = userStub.getUserById(
                UserRequest.newBuilder().setUserId(101).build()
        );
        return user.getName();
    }

}
