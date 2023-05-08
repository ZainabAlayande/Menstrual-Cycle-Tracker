package controller;

import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import services.UserService;
import services.UserServiceImpl;
import utils.UserExistException;

public class UserController {
    UserService userService = new UserServiceImpl();

    public Object register(RegisterRequest registerRequest){
        try {
            return userService.register(registerRequest);
        } catch (UserExistException exception) {
            return exception.getMessage();
        }
    }

    public Object login(LoginRequest loginRequest){
        try {
            return userService.login(loginRequest);
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }
    }


}
