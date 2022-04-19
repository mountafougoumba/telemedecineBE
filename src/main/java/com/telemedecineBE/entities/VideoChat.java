package com.telemedecineBE.entities;

import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
@Entity
@Table(name = "VIDEOCHAT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VideoChat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="VIDEO_CHAT_ROOM_ID")
    private String video_chat_room_id;
    @Column(name="CREATED_AT")
    private LocalDate created_at;
    @Column(name="DOCTOR_ID")
    private Integer doctor_id;
    @Column(name="PATIENT_ID")
    private Integer patient_id;

    public VideoChat(Integer doctor_id, Integer patient_id){
        super();
        this.video_chat_room_id = this.getChatRoomId();
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.created_at = LocalDate.now();
    }

    private String getChatRoomId() {
       return UUID.randomUUID().toString();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}