package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Serializable> {
    public Message findById(Integer id);

    public Boolean existsById(Integer id);

    public void deleteById(Integer id);
}
