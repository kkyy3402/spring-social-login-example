package example.spring_social_login.apis.oauth2;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import example.spring_social_login.apis.oauth2.dtos.kakao.KakaoUserProfileDto;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OAuth2Service {
    public KakaoUserProfileDto getUserKakaoProfile(String accessToken) throws IOException {
        String endpointUrl = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(endpointUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        try {
            ObjectMapper mapper = new ObjectMapper();
            KakaoUserProfileDto profile = mapper.readValue(conn.getInputStream(), KakaoUserProfileDto.class);
            return profile;
        } catch (JsonParseException e) {
            throw new IOException("Failed to parse JSON response", e);
        } catch (JsonMappingException e) {
            throw new IOException("Failed to map JSON response to object", e);
        }
    }

    public String getKaKaoAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            String sb = "grant_type=authorization_code" +
                    "&client_id=2bac0d15be64824c8bb10ee49464c9fa" + // TODO REST_API_KEY 입력
                    "&redirect_uri=http://localhost:8080/api/v1/oauth2/kakao/redirect" + // TODO 인가코드 받은 redirect_uri 입력
                    "&code=" + code;
            bw.write(sb);
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

}
