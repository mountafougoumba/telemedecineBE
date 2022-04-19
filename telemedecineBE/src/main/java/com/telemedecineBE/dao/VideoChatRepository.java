package com.telemedecineBE.dao;

import com.telemedecineBE.entities.VideoChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public interface VideoChatRepository extends JpaRepository<VideoChat, Serializable> {
    @Query("select v from VideoChat v where v.doctor_id = ?1 and v.patient_id = ?2")
    public VideoChat findVideoChatRoom(Integer doctor_id, Integer patient_id);
}
