package esprit.wd.user.dto;

import lombok.Builder;

@Builder
public record FailedType(
        String key,
        String value
) {
}
