package esprit.wd.password.user_client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
//        template.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJQQVJUSUFMTFlfU1VCU0NSSUJFUiJdLCJqdGkiOiI3ZGEyZjRlZC1mY2Y0LTQ2ODctYWY2MS1hZTExYmY1N2UxMjkiLCJzdWIiOiJzYWxoaW9tYXIzNjJAZ21haWwuY29tIiwiaWF0IjoxNzQ0MDQxMDI0LCJleHAiOjE3NDQ2NDU4MjR9.gQvxtfnFId4Y9yKn-poceyiLNil7ePSif9seQDb3YDU");
    }
}
