package esprit.wd.password.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@Document(collection = "passwordResetTokens")
public class PasswordResetToken {
    @Id
    private String id;

    private String token;

    private String userId;

    private Date expiryDate;

    public PasswordResetToken() {}

    public PasswordResetToken(String token, String userId) {
        this.token = token;
        this.userId = userId;
        this.expiryDate = calculateExpiryDate(24 * 60);  // Expire in 24 hours
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
