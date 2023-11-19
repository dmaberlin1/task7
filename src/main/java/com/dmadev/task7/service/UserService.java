package com.dmadev.task7.service;
import com.dmadev.task7.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    User readUser(int id);

    User deleteUser(int parseInt);

    void createOrUpdateUser(User user);
}
