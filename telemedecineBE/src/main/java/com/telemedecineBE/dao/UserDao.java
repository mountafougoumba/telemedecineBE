package com.telemedecineBE.dao;

import com.telemedecineBE.entities.User;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Transactional
public interface UserDao extends CrudRepository<User,Integer> {

    public User findByUserName(String username);


}

