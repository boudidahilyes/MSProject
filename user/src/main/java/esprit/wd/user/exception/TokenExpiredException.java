package esprit.wd.user.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String invalidToken) {
        super(invalidToken);
    }
}
