package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    void deleteUser(int id);
    void updateUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    //User setRolesForUser (User user);
}
