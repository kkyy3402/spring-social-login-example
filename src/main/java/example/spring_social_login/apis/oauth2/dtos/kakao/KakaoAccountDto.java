package example.spring_social_login.apis.oauth2.dtos.kakao;

import lombok.Data;

@Data
public class KakaoAccountDto {
    private Boolean profile_needs_agreement;
    private KakaoProfileDto profile;
    private Boolean has_email;
    private Boolean email_needs_agreement;
    private String email;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private Boolean has_age_range;
    private Boolean age_range_needs_agreement;
    private String age_range;
    private Boolean has_birthday;
    private Boolean birthday_needs_agreement;
    private String birthday;
    private String gender;

    // Getters and setters
    // ...
}
