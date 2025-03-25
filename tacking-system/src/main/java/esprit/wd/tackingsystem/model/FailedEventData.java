package esprit.wd.tackingsystem.model;


import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
public class FailedEventData {
    @Id
    private String eventId;

    List<FailedType> metadata;
}


