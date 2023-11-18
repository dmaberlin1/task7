package com.dmadev.task7.repositories;


import com.dmadev.task7.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    User readUser(int id);

    User deleteUser(int id);
}
