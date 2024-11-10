package cn.edu.fudan.advse.userservice.controller;

import cn.edu.fudan.advse.commonlibrary.lib.CourseRequest;
import cn.edu.fudan.advse.commonlibrary.lib.CourseResponse;
import cn.edu.fudan.advse.commonlibrary.lib.CourseServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GrpcClient("course-service")
    private CourseServiceGrpc.CourseServiceBlockingStub courseStub;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/course")
    public String testCourse() {
        CourseResponse course = courseStub.getCourseById(
                CourseRequest.newBuilder().setCourseId(1).build()
        );
        return course.getCourseName();
    }

}
