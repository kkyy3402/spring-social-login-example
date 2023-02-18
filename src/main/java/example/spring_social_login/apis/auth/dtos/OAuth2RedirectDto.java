package example.spring_social_login.apis.auth.dtos;

import lombok.Builder;
import lombok.Data;

public class OAuth2RedirectDto {

    @Data
    @Builder
    static public class Response {
        String platformType;
        String code;
        String accessToken;
    }

}
