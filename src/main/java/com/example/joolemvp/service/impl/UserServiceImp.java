package com.example.joolemvp.service.impl;

import com.example.joolemvp.Entity.User;
import com.example.joolemvp.Repository.UserRepository;
import com.example.joolemvp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        repository.save(user);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByUserName(String username) {
        return repository.findByUserName(username).orElse(null);
    }
}
