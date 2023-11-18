package com.dmadev.task7.repositories;

import com.dmadev.task7.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User readUser(int id) {
        return null;
    }

    @Override
    public User deleteUser(int id) {
        return null;
    }
}
