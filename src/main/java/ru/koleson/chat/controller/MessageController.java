package ru.koleson.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.koleson.chat.model.Message;
import ru.koleson.chat.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("person/{login}/room/{id}/")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/messageId")
    public Message findMessage(@PathVariable Long messageId) throws Exception {
        return messageService.findById(messageId);
    }

    @GetMapping
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @PostMapping
    public String create(@RequestBody Message message) throws Exception {
        messageService.createOrUpdate(message);
        return "200";
    }

    @PutMapping
    public String update(@RequestBody Message message) throws Exception {
        messageService.createOrUpdate(message);
        return "200";
    }
}
