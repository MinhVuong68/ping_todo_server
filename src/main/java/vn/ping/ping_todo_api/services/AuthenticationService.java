package vn.ping.ping_todo_api.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ping.ping_todo_api.error.UserAlreadyExistException;
import vn.ping.ping_todo_api.models.Role;
import vn.ping.ping_todo_api.models.User;
import vn.ping.ping_todo_api.models.request.AuthenticationRequest;
import vn.ping.ping_todo_api.models.request.RegisterRequest;
import vn.ping.ping_todo_api.models.response.AuthenticationResponse;
import vn.ping.ping_todo_api.repositories.UserRepository;
import vn.ping.ping_todo_api.security.JwtService;
import vn.ping.ping_todo_api.security.UserDetail;
import vn.ping.ping_todo_api.validation.UserValidator;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent())
            throw new UserAlreadyExistException("There is an account with that phone number!");
        else if(!UserValidator.validateName(request.getName()))
            throw new IllegalArgumentException("Name must have at least 2 characters!");
        else if(!UserValidator.validatePhoneNumber(request.getPhoneNumber()))
            throw new IllegalArgumentException("Invalid phone number!");
        else if(!UserValidator.validatePassword(request.getPassword()))
            throw new IllegalArgumentException("Password must have minimum eight characters, at least one letter and one number!");
        var user = User.builder()
                .name(request.getName())
                .avatar(request.getAvatar())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        User userSaved = userRepository.save(user);
        UserDetail userDetail = new UserDetail(user);
        var jwtToken = jwtService.generateToken(userDetail);
        AuthenticationResponse authResponse = modelMapper.map(userSaved,AuthenticationResponse.class);
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhoneNumber(),
                        request.getPassword()
                )
        );
        UserDetail userDetail = new UserDetail(user);
        var jwtToken = jwtService.generateToken(userDetail);
        AuthenticationResponse authResponse = modelMapper.map(user,AuthenticationResponse.class);
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}


