package esprit.wd.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
public record FailedEventData(
        List<FailedType> metadata
) {
}


