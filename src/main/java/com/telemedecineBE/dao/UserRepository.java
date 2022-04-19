package com.telemedecineBE.dao;

import com.telemedecineBE.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUserNameEquals(String name);

    Optional<User> findByUserName(String username);


}
