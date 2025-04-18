package com.msproject.complaint.controller;
import com.msproject.complaint.entity.User;
import com.msproject.complaint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class userController {
  private final UserRepository userService;
    @PostMapping("/add")
    public ResponseEntity<?> adduser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
