package com.telemedecineBE.web;

import com.telemedecineBE.entities.Message;
import org.springframework.web.bind.annotation.*;
import com.telemedecineBE.dao.MessageRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
public class MessageController {

    private MessageRepository messageRepository;

    MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @GetMapping("/messages")
    List<Message> getAllMessages(){
        System.out.println("getAllMessages");
        return messageRepository.findAll();
    }

    @GetMapping("/messages/sender_id={sender_id}")
    List<Message> getAllMessagesBySender(@PathVariable(value="sender_id")Integer sender_id){
        return messageRepository.findBySender(sender_id);
    }

    @GetMapping("/message/id={id}")
    Message getMessageById(@PathVariable(value="id")Integer id){
        Boolean exists = messageRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("message with id " + id + " does not exist");
        }
        System.out.println("getMessageById");
        return messageRepository.findById(id);
    }

    @PostMapping("/message")
    Message newMessage(@RequestBody Message message){
        //set date now
        message.setDate(LocalDate.now());
        //set time to now and format
        message.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        messageRepository.save(message);
        System.out.println("newMessage");
        return message;
    }

    @DeleteMapping("/message/id={id}")
    void deleteMessageById(@PathVariable(value="id") Integer id){
        Boolean exists = messageRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("message with id " + id + " does not exist.");
        }
        System.out.println("deleteMessageById");
        messageRepository.deleteById(id);
    }

    @PutMapping("/message/id={id}")
    Message updateMessage(@PathVariable(value="id")Integer id,
                          @RequestParam(required = false)String content){
        Boolean exists = messageRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("message with id " + id + " does not exist");
        }
        Message message = messageRepository.findById(id);

        if(content != null && content.length() > 0 && !content.equals(message.getContent())){
            message.setContent(content);
            message.setDate(LocalDate.now());
            //set time to now and format
            message.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }

        messageRepository.save(message);
        return message;
    }
}
