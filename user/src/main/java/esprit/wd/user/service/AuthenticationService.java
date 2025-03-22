package esprit.wd.user.service;


import esprit.wd.user.exception.TokenExpiredException;
import esprit.wd.user.exception.UserNameAlreadyExists;
import esprit.wd.user.exception.UserNotFoundException;
import esprit.wd.user.request.AuthenticationRequest;
import esprit.wd.user.request.RefreshTokenRequest;
import esprit.wd.user.request.RegisterRequest;
import esprit.wd.user.response.AuthenticationResponse;
import esprit.wd.user.response.RefreshResponse;
import esprit.wd.user.model.TokenBlackList;
import esprit.wd.user.repository.TokenBlackListRepository;
import esprit.wd.user.model.Role;
import esprit.wd.user.model.User;
import esprit.wd.user.repository.UserRepository;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenBlackListRepository tokenBlackListRepository;
    private final TokenBlackListService tokenBlackListService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public RefreshResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email()))
            throw new UserNameAlreadyExists("account already exists");

        var user2save = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(new ArrayList<>(List.of(Role.PARTIALLY_SUBSCRIBER)))
                .build();
        userRepository.save(user2save);
        var tokes = generateTokens(user2save);
        return new RefreshResponse(tokes.get(0), tokes.get(1));

    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new UserNotFoundException("Invalid email or password");
        }

        var user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        tokenBlackListService.removeUserTokens(user.getUserId());
        var tokes = generateTokens(user);
        return new AuthenticationResponse(
                tokes.get(0),
                tokes.get(1),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles()
        );
    }

    public RefreshResponse refresh(RefreshTokenRequest request) {
        var user = userRepository
                .findByEmail(request.username())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        tokenBlackListService.removeExpiredTokenByUser(
                user.getUserId(),
                jwtService.extractClaim(request.refreshToken(), Claims::getId)
        );
        return generateMainToken(user, request.refreshToken());
    }

    private ArrayList<String> generateTokens(User user) {
        var jwtToken = jwtService.generateToken(getHashMap(user.getRoles()), user);

        var jwtRefreshToken = jwtService.generateRefreshToken(user.getEmail());
        var refreshTokenBlackList = TokenBlackList.builder()
                .jti(jwtService.extractClaim(jwtRefreshToken, Claims::getId))
                .userId(user.getUserId())
                .created_at(LocalDateTime.now())
                .expires_at(null)
                .build();
        tokenBlackListRepository.save(refreshTokenBlackList);

        return new ArrayList<>(List.of(jwtToken, jwtRefreshToken));
    }

    private RefreshResponse generateMainToken(User user, String refreshToken) {
        if (jwtService.isTokenValid(refreshToken, user, user.getUserId())) {
            var jwtToken = jwtService.generateToken(getHashMap(user.getRoles()), user);
            return new RefreshResponse(jwtToken, refreshToken);
        } else
            throw new TokenExpiredException("JWT Token has expired");
    }

    private HashMap<String, Object> getHashMap(ArrayList<Role> roles) {
        HashMap<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("roles", roles);
        return claimsMap;
    }


}
