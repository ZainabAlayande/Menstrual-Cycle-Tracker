package data.repositories;

import data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    private UserRepository userRepository;
    private User user;
    private User userTwo;
    private User userThree;

    @BeforeEach public void setUp() {
        userRepository = new UserRepositoryImpl();
        user = new User();
        userTwo = new User();
        userThree = new User();

    }

     @Test public void saveOneUserCountIsOneTest() {
        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }

    @Test public void saveTwoUserCountIsTwoTest() {
        userRepository.save(user);
        userRepository.save(userTwo);
        assertEquals(2, userRepository.count());
    }

    @Test public void saveThreeUserCountIsThreeTest() {
        userRepository.save(user);
        userRepository.save(userTwo);
        userRepository.save(userThree);
        assertEquals(3, userRepository.count());
    }

    @Test public void saveThreeUser_DeleteOne_CountIsTwoTest() {
        userRepository.save(user);
        userRepository.save(userTwo);
        userRepository.save(userThree);
        userRepository.delete(user);
        assertEquals(2, userRepository.count());
    }

    @Test public void saveThreeUser_DeleteById_CountIsTwoTest() {
        userRepository.save(user);
        userRepository.save(userTwo);
        userRepository.save(userThree);
        userRepository.deleteUserById(1);
        assertEquals(2, userRepository.count());
    }

    @Test public void saveOneUser_FindUserByIdTest() {
        User savedUser = userRepository.save(user);
        User savedUserTwo = userRepository.save(userTwo);
        User savedUserThree = userRepository.save(userThree);
        assertEquals(3, userRepository.count());
        assertEquals("1", savedUser.getId());
        assertEquals("2", savedUserTwo.getId());
        assertEquals("3", savedUserThree.getId());
        assertEquals(savedUser, userRepository.findUserById(1));
    }

    @Test public void viewAllSavedUserTest() {
        user.setFirstName("Zainab");
        user.setLastName("Malik");
        user.setEmail("malik-zainab@gmail.com");
        userRepository.save(user);
        assertEquals(1, userRepository.count());
        String expected = "[User{" +
                "id='" + user.getId() + '\'' +
                ", firstName='" + user.getFirstName() + '\'' +
                ", lastName='" + user.getLastName() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                '}'+']';
        assertEquals(expected, userRepository.viewAllUser().toString());
    }
}