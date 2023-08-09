package vn.ping.ping_todo_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.ping.ping_todo_api.models.Role;
import vn.ping.ping_todo_api.models.User;
import vn.ping.ping_todo_api.models.request.AuthenticationRequest;
import vn.ping.ping_todo_api.models.request.RegisterRequest;
import vn.ping.ping_todo_api.models.response.AuthenticationResponse;
import vn.ping.ping_todo_api.repositories.UserRepository;
import vn.ping.ping_todo_api.security.JwtService;
import vn.ping.ping_todo_api.security.UserDetail;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .fullName(request.getFullName())
                .avatar(request.getAvatar())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        UserDetail userDetail = new UserDetail(user);
        var jwtToken = jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow();
        UserDetail userDetail = new UserDetail(user);
        var jwtToken = jwtService.generateToken(userDetail);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
