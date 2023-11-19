package com.dmadev.task7.service;

import com.dmadev.task7.model.User;
import com.dmadev.task7.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User readUser(int id) {
        return userRepository.readUser(id);
    }

    @Transactional(readOnly = false)
    @Override
    public User deleteUser(int parseInt) {
        User user = null;
        //Переменная user инициализируется значением null перед вызовом метода deleteUser,
        // чтобы в случае отсутствия пользователя с указанным id можно было вернуть null.
        try {
             user = userRepository.deleteUser(parseInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Transactional(readOnly = false)
    @Override
    public void createOrUpdateUser(User user) {
        if (user.getId() == 0) {
            createUser(user);
        }else {
            updateUser(user);
        }
    }


    private void createUser(User user) {
        userRepository.createUser(user);
    }


    private void updateUser(User user) {
        userRepository.updateUser(user);
    }


}
