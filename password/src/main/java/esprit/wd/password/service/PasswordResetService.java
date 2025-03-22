package esprit.wd.password.service;


import esprit.wd.password.exception.UserNotFoundException;
import esprit.wd.password.model.PasswordResetToken;
import esprit.wd.password.model.User;
import esprit.wd.password.repository.PasswordResetTokenRepository;
import esprit.wd.password.user_client.UserServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final UserServiceClient userServiceClient;

    public String forgotPassword(String email) {
        var user = userServiceClient.getUserByMail(email);
        System.out.println("user: " + user);

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user.getUserId());
        tokenRepository.save(passwordResetToken);

        emailService.sendResetPasswordEmail(passwordResetToken);

        return "Password reset email sent!";
    }

    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> passwordResetToken = tokenRepository.findByToken(token);
        return passwordResetToken.isPresent() && passwordResetToken.get().getExpiryDate().after(new Date());
    }

    public String resetPassword(String token, String newPassword) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        User user = userServiceClient.getUserByUserId(resetToken.getUserId());
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userServiceClient.updateUserPassword(user);
        tokenRepository.delete(resetToken);

        return "Password has been reset successfully";
    }
}
