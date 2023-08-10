package vn.ping.ping_todo_api.services;

import vn.ping.ping_todo_api.models.request.AuthenticationRequest;
import vn.ping.ping_todo_api.models.request.RegisterRequest;
import vn.ping.ping_todo_api.models.response.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
