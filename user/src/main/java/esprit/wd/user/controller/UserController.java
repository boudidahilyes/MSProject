package esprit.wd.user.controller;


import esprit.wd.user.response.UserResponse;
import esprit.wd.user.service.UserService;
import esprit.wd.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PatchMapping("/updateUserPassword")
    public ResponseEntity<?> updateUserPassword(
            @RequestBody User user
    ) {
        userService.updateUserPassword(user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getUserByMail/{email}")
    public ResponseEntity<UserResponse> getUserByMail(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok(userService.getUserByMail(email));
    }

    @GetMapping("/getUserByUserId/{userId}")
    public ResponseEntity<UserResponse> getUserByUserId(
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

}
