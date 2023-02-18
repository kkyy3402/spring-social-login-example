package example.spring_social_login.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private final static int SUCCESS = 200;
    private final static int BAD_REQUEST = 400;
    private final static int NOT_FOUND = 404;
    private final static int FAILED = 500;
    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String BAD_REQUEST_MESSAGE = "잘못된 접근입니다.";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private final static String FAILED_MESSAGE = "서버에서 오류가 발생하였습니다.";
    private final static String INVALID_ACCESS_TOKEN = "Invalid access token.";
    private final static String INVALID_REFRESH_TOKEN = "Invalid refresh token.";
    private final static String NOT_EXPIRED_TOKEN_YET = "Not expired token yet.";

    private final ApiResponseHeader header;
    private final Map<String, T> body;

    public static <T> ApiResponse<T> success(String name, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(name, body);

        return new ApiResponse(new ApiResponseHeader(SUCCESS, SUCCESS_MESSAGE), map);
    }

    public static <T> ApiResponse<T> success(String name, Object body, Long itemCount) {
        Map<String, Object> map = new HashMap<>();
        map.put(name, body);
        map.put("totalItemCount", itemCount);

        return new ApiResponse(new ApiResponseHeader(SUCCESS, SUCCESS_MESSAGE), map);
    }

    public static <T> ApiResponse<T> badRequest() {
        return new ApiResponse(new ApiResponseHeader(BAD_REQUEST, BAD_REQUEST_MESSAGE), null);
    }

    public static <T> ApiResponse<T> notFound() {
        return new ApiResponse(new ApiResponseHeader(NOT_FOUND, NOT_FOUND_MESSAGE), null);
    }

    public static <T> ApiResponse<T> fail() {
        return new ApiResponse(new ApiResponseHeader(FAILED, FAILED_MESSAGE), null);
    }

    public static <T> ApiResponse<T> invalidAccessToken() {
        return new ApiResponse(new ApiResponseHeader(FAILED, INVALID_ACCESS_TOKEN), null);
    }

    public static <T> ApiResponse<T> invalidRefreshToken() {
        return new ApiResponse(new ApiResponseHeader(FAILED, INVALID_REFRESH_TOKEN), null);
    }

    //400
    public static <T> ResponseEntity<T> badRequest(String message, ApiResponseCode.FAIL errorEnum) {
        ApiResponse apiResponse = new ApiResponse(new ApiResponseHeader(errorEnum.getErrCode(), message), null);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }

    //401
    public static <T> ResponseEntity<T> unauthorized(String message, ApiResponseCode.FAIL errorEnum) {
        ApiResponse apiResponse = new ApiResponse(new ApiResponseHeader(errorEnum.getErrCode(), message), null);
        return new ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    //403
    public static <T> ResponseEntity<T> forbidden(String message, ApiResponseCode.FAIL errorEnum) {
        ApiResponse apiResponse = new ApiResponse(new ApiResponseHeader(errorEnum.getErrCode(), message), null);
        return new ResponseEntity(apiResponse, HttpStatus.FORBIDDEN);
    }

    //500
    public static <T> ResponseEntity<T> internalServerError(String message, ApiResponseCode.FAIL errorEnum) {
        ApiResponse apiResponse = new ApiResponse(new ApiResponseHeader(errorEnum.getErrCode(), message), null);
        return new ResponseEntity(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ApiResponse<T> notExpiredTokenYet() {
        return new ApiResponse(new ApiResponseHeader(FAILED, NOT_EXPIRED_TOKEN_YET), null);
    }
}