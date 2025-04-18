package esprit.wd.user.dto;

public record UserRequest(
        String userId,
        String password
) {
}
