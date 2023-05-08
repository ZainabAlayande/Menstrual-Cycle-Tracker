package data.repositories;

import data.models.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    long count();

    User findUserById(int id);

    User findUserByEmail(String email);
    List<User> viewAllUser();

    void delete(User user);

    void deleteUserById(int id);
}
