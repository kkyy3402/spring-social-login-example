package example.spring_social_login.apis.auth;

import example.spring_social_login.apis.auth.dtos.OAuth2RedirectDto;
import example.spring_social_login.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/oauth2")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.host}")
    private String dbHost;

    @Value("${db.password}")
    private String password;

    @GetMapping("/{platformType}/redirect")
    public ApiResponse redirect(
            @PathVariable("platformType") String platformType,
            @RequestParam String code) {

        String accessToken = authService.getKaKaoAccessToken(code);

        OAuth2RedirectDto.Response response = OAuth2RedirectDto.Response
                .builder()
                .platformType(platformType)
                .accessToken(accessToken)
                .code(code)
                .build();

        return ApiResponse.success("data", response);
    }
}