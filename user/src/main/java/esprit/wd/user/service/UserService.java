package esprit.wd.user.service;


import esprit.wd.user.dto.UserRequest;
import esprit.wd.user.exception.DataMismatchException;
import esprit.wd.user.response.UserResponse;
import esprit.wd.user.model.User;
import esprit.wd.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public String getUserByMail(String request) {
        var user = userRepository.findByEmail(request).orElse(null);
        assert user != null;
        return user.getUserId();
    }

    public User getUserByUserId(String request) {
        return userRepository.findById(request).orElse(null);
    }

    public List<UserResponse> getUsers() {
        var users = userRepository.findAll();

        if (users == null)
            return null;

        return users.stream().<UserResponse>map((u) ->
                UserResponse.builder()
                        .userId(u.getUserId())
                        .password(u.getPassword())
                        .email(u.getEmail())
                        .build()
        ).toList();
    }

    public void updateUserPassword(UserRequest request) {
        var user = getUserByUserId(request.userId());
        System.out.println("user: " + user);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }


}
