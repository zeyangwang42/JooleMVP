package com.example.joolemvp.service.impl;

import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.User;
import com.example.joolemvp.Enums.Role;
import com.example.joolemvp.Repository.UserRepository;
import com.example.joolemvp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.Collection;
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
        user.setRole(Role.USER);
        repository.save(user);
        return user;
    }
    @Override
    public User createAdmin(User user) {
        user.setRole(Role.ADMIN);
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

    @Override
    public boolean volidUser(User user) {
        if(user==null||user.getUserName()==null||user.getPassword()==null){
           return false;
        }
        return true;
    }

    @Override
    public String getUserName(User user) {
        return user.getUserName();
    }

    @Override
    public String getPassword(User user) {
        return user.getPassword();
    }

    @Override
    public Collection<Project> getUserProject(User user) {
        return user.getProjects();
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public boolean update(User user, User updateUser) {
        if(user.getUserName()!=updateUser.getUserName()&&updateUser.getUserName()!=null){
            return false;
        }
        if(user.getPassword()!=updateUser.getPassword()&&updateUser.getPassword()!=null){
            user.setPassword(updateUser.getPassword());
        }
        if(user.getEmail()!=updateUser.getEmail()&&updateUser.getEmail()!=null){
            user.setEmail(updateUser.getEmail());
        }
        if(user.getFirstName()!=updateUser.getFirstName()&&updateUser.getFirstName()!=null){
            user.setFirstName(updateUser.getFirstName());
        }
        if(user.getLastName()!=updateUser.getLastName()&&updateUser.getLastName()!=null){
            user.setLastName(updateUser.getLastName());
        }
        return true;
    }
}
