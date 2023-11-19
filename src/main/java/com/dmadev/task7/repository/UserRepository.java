package com.dmadev.task7.repository;


import com.dmadev.task7.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    User readUser(int id);

    User deleteUser(int id);
}
