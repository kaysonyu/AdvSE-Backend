package cn.edu.fudan.advse.userservice.grpc;

import cn.edu.fudan.advse.commonlibrary.lib.UserRequest;
import cn.edu.fudan.advse.commonlibrary.lib.UserResponse;
import cn.edu.fudan.advse.commonlibrary.lib.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUserById(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        // just for test
        int userId = request.getUserId();
        UserResponse response = UserResponse.newBuilder()
                .setUserId(userId)
                .setName("John Doe")
                .setEmail("john.doe@example.com")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
