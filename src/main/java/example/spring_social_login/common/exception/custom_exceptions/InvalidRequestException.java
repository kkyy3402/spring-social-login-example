package example.spring_social_login.common.exception.custom_exceptions;

import example.spring_social_login.common.response.ApiResponseMessages;

public class InvalidRequestException extends RuntimeException {
    @Override
    public String getMessage() {
        return ApiResponseMessages.FAIL.INVALID_REQUEST;
    }
}
