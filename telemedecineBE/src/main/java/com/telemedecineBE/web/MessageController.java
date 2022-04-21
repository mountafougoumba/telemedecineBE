package com.telemedecineBE.web;

import com.telemedecineBE.entities.Message;
import com.telemedecineBE.enumeration.MessageType;
import org.springframework.web.bind.annotation.*;
import com.telemedecineBE.dao.MessageRepository;

import com.telemedecineBE.Security.AES;
import java.time.LocalDate;
import java.util.List;

@RestController
public class MessageController {
    final String secretKey = "ssshhhhhhhhhhh!!!!";

    private MessageRepository messageRepository;

    MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @GetMapping("/messages")
    List<Message> getAllMessages(){
        List<Message> messages = messageRepository.findAll();
        messages.forEach(message -> {
            message.setContent(AES.decrypt(message.getContent(), this.secretKey));
        });
        return messages;
    }

    @GetMapping("/messages/sender_id={sender_id}")
    List<Message> getAllMessagesBySender(@PathVariable(value="sender_id")Integer sender_id){
        List<Message> messages = messageRepository.findBySender(sender_id);
        messages.forEach(message -> {
            message.setContent(AES.decrypt(message.getContent(), this.secretKey));
        });
        return messages;
    }

    @GetMapping("/messages/messageType={messageType}")
    List<Message> getAllMessagesByType(@PathVariable(value = "messageType") MessageType messageType){
        List<Message> messages = messageRepository.findByMessageType(messageType);
        messages.forEach(message -> {
            message.setContent(AES.decrypt(message.getContent(), this.secretKey));
        });
        return messages;
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
        String encodedMessage = AES.encrypt(message.getContent(), this.secretKey);
        message.setContent(encodedMessage);
        messageRepository.save(message);
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
        }

        messageRepository.save(message);
        return message;
    }
}
