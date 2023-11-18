package com.dmadev.task7.services;
import com.dmadev.task7.models.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User readUser(int id);

    User deleteUser(int parseInt);

    void createOrUpdateUser(User user);
}
