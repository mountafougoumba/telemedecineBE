package com.telemedecineBE.dao;

import com.telemedecineBE.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public interface MessageRepository extends JpaRepository<Message, Serializable> {

    @Query("select m from Message m where m.sender_id = ?1 or m.receiver_id = ?1")
    public List<Message> findBySender(Integer sender_id);

    public Message findById(Integer id);

    public Boolean existsById(Integer id);

    public void deleteById(Integer id);
}
