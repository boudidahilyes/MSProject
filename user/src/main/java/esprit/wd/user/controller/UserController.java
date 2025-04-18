package esprit.wd.user.controller;


import esprit.wd.user.dto.UserRequest;
import esprit.wd.user.response.UserResponse;
import esprit.wd.user.service.UserService;
import esprit.wd.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PutMapping("/updateUserPassword")
    public ResponseEntity<?> updateUserPassword(
            @RequestBody UserRequest user
    ) {
        userService.updateUserPassword(user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getUserByMail/{email}")
    public ResponseEntity<String> getUserByMail(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok(userService.getUserByMail(email));
    }


    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
