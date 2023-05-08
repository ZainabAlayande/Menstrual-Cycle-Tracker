package services;

import data.models.User;
import data.repositories.UserRepository;
import data.repositories.UserRepositoryImpl;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import dtos.responses.LoginResponse;
import dtos.responses.RegisterResponse;
import utils.Mapper;
import utils.UserExistException;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws UserExistException {
        if (validateUserEmail(registerRequest.getEmail()))
            throw new UserExistException(registerRequest.getFirstName() + " " + "already exist");

        User savedUser = Mapper.map(registerRequest);
        userRepository.save(savedUser);
        RegisterResponse registerResponse = Mapper.map(savedUser);
        return registerResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validateAccountNotFoundNotFound(loginRequest);
        User foundAccount = userRepository.findUserByEmail(loginRequest.getEmailAddress());
        LoginResponse response = new LoginResponse();
        Mapper.map(foundAccount, response);
        return response;
    }

    private void validateAccountNotFoundNotFound(LoginRequest loginRequest) {
        User foundAccount = userRepository.findUserByEmail(loginRequest.getEmailAddress());
        User foundParty = userRepository.findUserByEmail(loginRequest.getEmailAddress());




    }
    private boolean validateUserEmail(String email) {
        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser != null)
            return true;
        return false;
    }
}
