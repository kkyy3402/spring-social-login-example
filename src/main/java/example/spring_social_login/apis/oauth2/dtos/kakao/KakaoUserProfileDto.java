package example.spring_social_login.apis.oauth2.dtos.kakao;

import lombok.Data;

@Data
public class KakaoUserProfileDto {
    private Long id;
    private String connected_at;
    private KakaoAccountDto kakao_account;
}