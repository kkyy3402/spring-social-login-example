package example.spring_social_login.apis.oauth2;

import example.spring_social_login.apis.oauth2.dtos.kakao.KakaoUserProfileDto;
import example.spring_social_login.common.Constants;
import example.spring_social_login.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/oauth2")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service OAuth2Service;
    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.password}")
    private String password;

    @GetMapping("/{platformType}/redirect")
    public ApiResponse redirect(
            @PathVariable("platformType") Constants.PlatformType platformType,
            @RequestParam String accessToken) throws IOException {

        if (platformType == Constants.PlatformType.kakao) {
            KakaoUserProfileDto profile = OAuth2Service.getUserKakaoProfile(accessToken);
            return ApiResponse.success(profile);
        }

        return ApiResponse.badRequest();
    }
}