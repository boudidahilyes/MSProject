package esprit.wd.user.dto;


import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventData implements Serializable {
    private String eventType;
    private String email;
    private Map<String, Object> metadata;
}