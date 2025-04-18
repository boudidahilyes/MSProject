package esprit.wd.password.service;


import esprit.wd.password.dto.UserRequest;
import esprit.wd.password.model.PasswordResetToken;
import esprit.wd.password.repository.PasswordResetTokenRepository;
import esprit.wd.password.request.ForgotPassword;
import esprit.wd.password.user_client.UserServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        var userId = userServiceClient.getUserByMail(email);

        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, userId);
        tokenRepository.save(passwordResetToken);

        emailService.sendResetPasswordEmail(passwordResetToken, email);

        return "Password reset email sent!";
    }

    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> passwordResetToken = tokenRepository.findByToken(token);
        return passwordResetToken.isPresent() && passwordResetToken.get().getExpiryDate().after(new Date());
    }

    public String resetPassword(String token, ForgotPassword request) {

        if (request.confirmNewPassword().equals(request.newPassword())) {
            PasswordResetToken resetToken = tokenRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Invalid token"));


            var user = UserRequest.builder()
                    .userId(resetToken.getUserId())
                    .password(request.newPassword().trim())
                    .build();
            userServiceClient.updateUserPassword(user);
            tokenRepository.delete(resetToken);

            return "Password has been reset successfully";
        }
        throw new RuntimeException("Invalid or expired token or password");
    }
}
