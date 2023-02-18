package example.spring_social_login.common.exception;

import example.spring_social_login.common.exception.custom_exceptions.InvalidRequestException;
import example.spring_social_login.common.response.ApiResponse;
import example.spring_social_login.common.response.ApiResponseCode;
import example.spring_social_login.common.response.ApiResponseHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice()
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity invalidRequestExceptionHandler(Exception e, WebRequest request) {
        return ApiResponse.badRequest(e.getMessage(), ApiResponseCode.FAIL.InvalidRequest);
    }

    //나머지 Exception들 처리
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        ApiResponseHeader header = new ApiResponseHeader(ApiResponseCode.FAIL.CommonError.getErrCode(), ex.getLocalizedMessage());
        ApiResponse<Object> apiResponse = new ApiResponse<>(header, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
