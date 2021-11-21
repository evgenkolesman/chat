package ru.koleson.chat.controller;

import javassist.NotFoundException;
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

    @GetMapping("/{messageId}")
    public Message findMessage(@PathVariable Long messageId) throws Exception {
        return messageService.findById(messageId);
    }

    @GetMapping
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @PostMapping
    public String create(@RequestBody Message message) throws Exception {
        messageService.create(message);
        return "200";
    }

    @PutMapping("/{messageId}")
    public String update(@RequestBody Message message) throws Exception {
        messageService.update(message);
        return "200";
    }

    @DeleteMapping("{/messageId}")
    public String delete(@PathVariable Long id) throws NotFoundException {
        messageService.deleteMessage(id);
        return "200";
    }
}
