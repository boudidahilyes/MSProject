package esprit.wd.user.dto;


import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent implements Serializable {
    private String eventType;
    private String userId;
    private Map<String, Object> metadata;
}