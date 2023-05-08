package services;

import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import dtos.responses.LoginResponse;
import dtos.responses.RegisterResponse;
import utils.UserExistException;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest) throws UserExistException;

    LoginResponse login(LoginRequest loginRequest);

}
