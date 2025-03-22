package esprit.wd.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class TokenBlackList {

    @Id
    private String tokenId;

    private String jti;

    private LocalDateTime created_at;

    private LocalDateTime expires_at;

    private String userId;

}
