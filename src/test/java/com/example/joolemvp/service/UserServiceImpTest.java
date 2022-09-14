package com.example.joolemvp.service;

import com.example.joolemvp.Entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImpTest {
    @Autowired
    private UserService service;

    int id;


    @BeforeAll
    void createUser() {
        User user = new User();
        user.setFirstName("122");
        user.setLastName("211");
        user.setEmail("email");
        user.setPhone("phone");
        user.setCreateTime(new Date());
        service.createUser(user);
        id=user.getUserId();
    }

    @Test
    void findById() {
        Assertions.assertNotNull(service.findById(id));
    }


    @AfterAll
    void deleteById() {

        service.deleteById(id);

    }

    @Test
    void findAll() {
        Assertions.assertNotNull(service.findAll());
    }
}