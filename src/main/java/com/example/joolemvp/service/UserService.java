package com.example.joolemvp.service;

import com.example.joolemvp.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User findById(Integer id);

    public User createUser(User user);

    public void deleteById(Integer id);

    public List<User> findAll();
    public User findByUserName(String username);

}
