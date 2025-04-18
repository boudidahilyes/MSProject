package esprit.wd.user.response;

public record RefreshResponse(
        String token,
        String refreshToken
) {
}
