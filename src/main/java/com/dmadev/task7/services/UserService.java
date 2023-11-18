package com.dmadev.task7.services;
import com.dmadev.task7.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    User readUser(int id);

    User deleteUser(int parseInt);

    void createOrUpdateUser(User user);
}
