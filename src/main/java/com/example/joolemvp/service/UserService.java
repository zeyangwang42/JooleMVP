package com.example.joolemvp.service;

import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {
    public User findById(Integer id);

    public User createUser(User user);

    public void deleteById(Integer id);

    public List<User> findAll();
    public User findByUserName(String username);
    public boolean volidUser(User user);
    public String getUserName(User user);
    public String getPassword(User user);
    public Collection<Project> getUserProject(User user);
    public void delete(User user);
    public boolean update(User user,User updateUser);
    public User createAdmin(User user);

}
