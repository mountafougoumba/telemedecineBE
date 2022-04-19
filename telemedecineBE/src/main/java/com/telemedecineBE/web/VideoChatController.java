package com.telemedecineBE.web;

import com.telemedecineBE.dao.VideoChatRepository;
import com.telemedecineBE.entities.VideoChat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoChatController {
    private VideoChatRepository vcr;

    VideoChatController(VideoChatRepository vcr) {
        this.vcr = vcr;
    }

    @GetMapping("/video-chat-room/{doctor_id}/{patient_id}")
    VideoChat getVideoChatRoom(
            @PathVariable(value="doctor_id")Integer doctor_id,
            @PathVariable(value="patient_id")Integer patient_id
    ) {
        return this.vcr.findVideoChatRoom(doctor_id, patient_id);
    }

    @PostMapping("/video-chat-room/{doctor_id}/{patient_id}")
    VideoChat createChatRoom(
            @PathVariable(value="doctor_id")Integer doctor_id,
            @PathVariable(value="patient_id")Integer patient_id
    ) {
        VideoChat vc = new VideoChat(doctor_id, patient_id);
        this.vcr.save(vc);
        return vc;
    }

    void verifyDoctorAndPatient(Integer doctor_id, Integer patient_id) {
        Boolean doctorExist = this.vcr.existsById(doctor_id);
        if(!doctorExist){
            throw new IllegalStateException("Doctor with id " + doctor_id + " does not exist");
        }
        Boolean patientExist = this.vcr.existsById(patient_id);
        if(!patientExist){
            throw new IllegalStateException("Patient with id " + patientExist + " does not exist");
        }
    }
}
