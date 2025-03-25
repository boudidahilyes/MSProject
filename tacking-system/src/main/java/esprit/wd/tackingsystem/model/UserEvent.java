package esprit.wd.tackingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "successful_conn")
public class UserEvent {

    @Id
    private String eventId;

    private String eventType;

    private Instant timestamp = Instant.now();

    private String userId;

    private Map<String, Object> metadata;
}