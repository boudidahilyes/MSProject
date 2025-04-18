package esprit.wd.user.response;

import esprit.wd.user.model.Role;

import java.util.ArrayList;

public record AuthenticationResponse(
        String token,
        String refreshToken,
        String firstname,
        String lastname,
        String email,
        ArrayList<Role> roles
) {
}
