package com.dmadev.task7.services;
import com.dmadev.task7.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User readUser(int id) {
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public User deleteUser(int parseInt) {
        return null;
    }

    @Transactional(readOnly = false)
    @Override
    public void createOrUpdateUser(User user) {

    }
}
