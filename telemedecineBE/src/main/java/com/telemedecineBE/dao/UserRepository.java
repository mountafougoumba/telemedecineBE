package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Doctor;
import com.telemedecineBE.entities.Message;
import com.telemedecineBE.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUserNameEquals(String name);

    Optional<User> findByUserName(String username);

    @Query("select u from User u where u.fname like %?1% or u.lname like %?1%")
    public List<User> searchUsers(String term);
}
