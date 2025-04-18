package esprit.wd.tackingsystem.model;

import lombok.Builder;

@Builder
public record FailedType(
        String key,
        String value
) {
}
