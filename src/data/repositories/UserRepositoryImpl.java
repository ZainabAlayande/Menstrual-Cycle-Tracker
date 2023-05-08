package data.repositories;

import data.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository{

    private int count;
    private List<User> users = new ArrayList<>();


    @Override
    public User save(User user) {
        boolean userHasNotBeenSaved = user.getId() == null;
        if (userHasNotBeenSaved)
            user.setId(String.valueOf(generateUserId()));
        users.add(user);
        count++;
        return user;
    }

    private int generateUserId() {
        return count + 1;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public User findUserById(int id) {
        for (User user : users) {
            if (Objects.equals(user.getId(), String.valueOf(id)))
                return user;
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user: users) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    @Override
    public List<User> viewAllUser() {
        return users;
    }

    @Override
    public void delete(User userObject) {
        for (User user: users) {
            if (user.equals(userObject))
                users.remove(user);
            break;
        }
        count--;
    }

    @Override
    public void deleteUserById(int id) {
        for (User user: users) {
            if (Objects.equals(user.getId(), String.valueOf(id)))
                users.remove(user);
            break;
        }
        count--;
    }
}
