package esprit.wd.password.user_client;


import esprit.wd.password.dto.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "user", url = "${application.password.user.url}")
public interface UserServiceClient {

    @GetMapping("/getUserByMail/{email}")
    String getUserByMail(@PathVariable("email") String email);

    @PutMapping("/updateUserPassword")
    void updateUserPassword(@RequestBody UserRequest request);
}