package esprit.wd.password.user_client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJQQVJUSUFMTFlfU1VCU0NSSUJFUiJdLCJqdGkiOiIyN2QzMWFjOS1mMTY3LTRlNWMtYTM1ZC00NGNkZDQwZDljNzkiLCJzdWIiOiJzYWxoaW9tYXIzNjJAZ21haWwuY29tIiwiaWF0IjoxNzQyNTk5NTM4LCJleHAiOjE3NDMyMDQzMzh9.AEVwuAI9l9mHlDOe2pKxxKPLIQE1LuQ7J0rRIN4Sn5c");
    }
}
