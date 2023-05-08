package utils;

import data.models.User;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import dtos.responses.LoginResponse;
import dtos.responses.RegisterResponse;

public class Mapper {

    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        return user;
    }

    public static RegisterResponse map(User user) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(user.getId());
        registerResponse.setFullName(user.getFirstName() + " " + user.getLastName());
        registerResponse.setEmail(user.getEmail());
        return registerResponse;
    }

    public static void map(User foundAccount, LoginResponse response) {
        response.setFullName(foundAccount.getFirstName() + " " + foundAccount.getLastName());
        response.setEmailAddress(foundAccount.getEmail());
    }
}
