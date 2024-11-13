import lombok.Data

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {

    @NoNull
    private int statusCode;  // 状态码
    private String message;  // 消息
    private T data;          // 数据（可选）

    public ApiResponse(int statusCode, String message) {
        this(statusCode, message, null);
    }
}