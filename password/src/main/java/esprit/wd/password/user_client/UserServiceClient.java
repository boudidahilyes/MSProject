package esprit.wd.password.user_client;


import esprit.wd.password.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "user", url = "${application.password.user.url}")
public interface UserServiceClient {

    @GetMapping("/getUserByMail/{email}")
    User getUserByMail(@PathVariable("email") String email);

    @GetMapping("/getUserByUserId/{userId}")
    User getUserByUserId(@PathVariable("userId") String userId);

    @PatchMapping("/updateUserPassword")
    void updateUserPassword(@RequestBody User user);
}