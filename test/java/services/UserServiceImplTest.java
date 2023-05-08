package services;

import data.repositories.UserRepository;
import dtos.requests.RegisterRequest;
import dtos.responses.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.UserExistException;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();

    @Test public void userCanRegisterTest() throws UserExistException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("Zainab");
        registerRequest.setLastName("Malik");
        registerRequest.setEmail("malik-zainab@gmail.com");
        registerRequest.setPassword("password");
        String expected = """
                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                Id: 1
                Full Name: Zainab Malik
                Email: malik-zainab@gmail.com
                """;
        RegisterResponse actual = userService.register(registerRequest);
        assertEquals(expected, actual.toString());
    }

    @Test public void userWithSameEmailThrowException() throws UserExistException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("malik-jumia@gmail.com");
        userService.register(registerRequest);

        RegisterRequest registerRequestTwo = new RegisterRequest();
        registerRequestTwo.setEmail("malik-jumia@gmail.com");
        assertThrows(UserExistException.class, ()-> userService.register(registerRequestTwo));
    }

}