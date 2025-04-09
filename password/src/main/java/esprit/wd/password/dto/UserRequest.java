package esprit.wd.password.dto;

import lombok.Builder;

@Builder
public record UserRequest(
        String userId,
        String password
) {
}
