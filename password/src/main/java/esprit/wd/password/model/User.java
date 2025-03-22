package esprit.wd.password.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    String userId;
    String email;
    String password;
}
