package com.telemedecineBE.dao;

import com.telemedecineBE.entities.User;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

@Transactional
public interface UserDao extends JpaRepository<User, Serializable> {

    public User findByUserName(String username);

    public User findUserByUserNameAndUserpassword(String username, String password);

    public User findById(Integer id);

    public User findByEmail(String email);

    public User findByCellphone(String phone);

    public Boolean existsById(Integer id);

    public Boolean existsByEmail(String email);

    public Boolean existsByCellphone(String phone);

    public Boolean existsByUserName(String username);

    public void deleteById(Integer id);

    public void deleteByEmail(String email);

    public void deleteByCellphone(String phone);




}

