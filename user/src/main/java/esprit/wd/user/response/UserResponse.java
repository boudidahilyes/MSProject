package esprit.wd.user.response;


import lombok.Builder;

@Builder
public record UserResponse(
        String userId,
        String email,
        String password
) {
}
