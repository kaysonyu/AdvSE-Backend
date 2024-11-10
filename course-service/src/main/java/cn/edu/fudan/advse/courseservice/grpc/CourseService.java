package cn.edu.fudan.advse.courseservice.grpc;

import cn.edu.fudan.advse.commonlibrary.lib.CourseRequest;
import cn.edu.fudan.advse.commonlibrary.lib.CourseResponse;
import cn.edu.fudan.advse.commonlibrary.lib.CourseServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CourseService extends CourseServiceGrpc.CourseServiceImplBase {

    @Override
    public void getCourseById(CourseRequest request, StreamObserver<CourseResponse> responseObserver) {
        // just for test
        int courseId = request.getCourseId();
        CourseResponse response = CourseResponse.newBuilder()
                .setCourseId(courseId)
                .setCourseName("Advanced Software Engineering")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
