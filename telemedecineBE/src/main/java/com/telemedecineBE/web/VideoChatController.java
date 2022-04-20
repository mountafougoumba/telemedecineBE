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
        VideoChat vc = this.vcr.findVideoChatRoom(doctor_id, patient_id);

        if(vc == null) {
            vc = new VideoChat(doctor_id, patient_id);
            this.vcr.save(vc);
        }

        return vc;
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

}
