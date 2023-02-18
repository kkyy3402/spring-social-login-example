package example.spring_social_login.common.response;

public class ApiResponseCode {

    static Integer ERR_CODE_PREFIX = 1000;

    public enum FAIL {
        CommonError,
        UserNotFound,
        AlreadyExistUserExcption,
        HasNoUser,
        DataNotFound,
        SessionNotExist,
        InvalidSession,
        ExpiredSession,
        InvalidRequest,
        AlreadyExistLike,
        ReportTargetNotExist,
        FirebaseException;

        public int getErrCode() {
            return ordinal() + ERR_CODE_PREFIX;
        }
    }

}