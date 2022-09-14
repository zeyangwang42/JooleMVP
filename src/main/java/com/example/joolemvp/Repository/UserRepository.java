package com.example.joolemvp.Repository;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {

    Optional<User> findByUserName(String username);

}
