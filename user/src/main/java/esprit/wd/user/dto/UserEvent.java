package esprit.wd.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor  // <- This is critical
@AllArgsConstructor
public class UserEvent {
    private String eventType;
    private String userId;
    private Map<String, Object> metadata;
}