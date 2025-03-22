package esprit.wd.password.service;

import esprit.wd.password.exception.EmailSendFailedException;
import esprit.wd.password.exception.UserNotFoundException;
import esprit.wd.password.model.PasswordResetToken;
import esprit.wd.password.repository.PasswordResetTokenRepository;
import esprit.wd.password.user_client.UserServiceClient;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final PasswordResetTokenRepository tokenRepository;
    private final UserServiceClient userServiceClient;

    @Async
    public void sendResetPasswordEmail(PasswordResetToken passwordResetToken) {
        var user=userServiceClient.getUserByUserId(passwordResetToken.getUserId());
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject("Reset your password");
            mimeMessageHelper.setText("""
                    <div>
                      <a href="http://localhost:8888/api/v1/password/resetPassword?token=%s" target="_blank">click link to reset</a>
                    </div>
                    """.formatted(passwordResetToken.getToken()), true);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            log.error("Failed to send password reset email to {}", user.getEmail());
            tokenRepository.delete(passwordResetToken);
            throw new EmailSendFailedException("Failed to send email to " + user.getEmail());
        }
    }
}
