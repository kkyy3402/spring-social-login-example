package example.spring_social_login.apis.oauth2.dtos.kakao;

import lombok.Data;

@Data
public class KakaoProfileDto {
    private String nickname;
    private String thumbnail_image_url;
    private String profile_image_url;

    // Getters and setters
    // ...
}