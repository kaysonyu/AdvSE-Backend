package cn.edu.fudan.advse.commonlibrary.utils;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int statusCode;  // 状态码
    private String message;  // 消息
    private T data;          // 数据（可选）

    // 全参构造函数
    public ApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    // 静态工厂方法：生成成功响应
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }

    // 不含返回对象的成功响应
    public static <T> ApiResponse<T> success() { return new ApiResponse<>(200, "success", null); }

    // 静态工厂方法：生成失败响应
    public static <T> ApiResponse<T> fail(int statusCode, String message) {
        return new ApiResponse<>(statusCode, message, null);
    }
}
