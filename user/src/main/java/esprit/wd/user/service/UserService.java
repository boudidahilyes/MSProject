package esprit.wd.user.service;


import esprit.wd.user.exception.DataMismatchException;
import esprit.wd.user.response.UserResponse;
import esprit.wd.user.model.User;
import esprit.wd.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponse getUserByMail(String request) {
        var user = userRepository.findByEmail(request).orElse(null);
        if (user == null)
            return null;

        return UserResponse.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public UserResponse getUserByUserId(String request) {
        var user = userRepository.findById(request).orElse(null);
        if (user == null)
            return null;

        return UserResponse.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public void updateUserPassword(User user) {
        userRepository.save(user);
    }


}
