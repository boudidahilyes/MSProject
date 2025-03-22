package esprit.wd.user.controller;


import esprit.wd.user.response.UserResponse;
import esprit.wd.user.service.AuthenticationService;
import esprit.wd.user.request.AuthenticationRequest;
import esprit.wd.user.request.RefreshTokenRequest;
import esprit.wd.user.request.RegisterRequest;
import esprit.wd.user.response.AuthenticationResponse;
import esprit.wd.user.response.RefreshResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RefreshResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> login(
            @RequestBody @Valid RefreshTokenRequest request
    ) {
        return ResponseEntity.ok(authenticationService.refresh(request));
    }


}
