package ru.koleson.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.koleson.chat.service.PersonService;

/**
 * Надо продумать логику вхождения в комнату
 */

@RestController
@RequestMapping("person/{login}/room")
@RequiredArgsConstructor
public class RoomController {

    private final PersonService personService;

    @PostMapping("/")
    public String createRoom(@PathVariable String login) throws Exception {
        personService.updatePersonRoom(0L, login);
        return "200";
    }

    @PostMapping("/private")
    public String createPrivate(@PathVariable String login) throws Exception {
        personService.updatePersonPrivateRoom(0L, login);
        return "200";
    }


    @PutMapping("/{id}")
    public String joinRoom(@PathVariable String login, @PathVariable Long id) throws Exception {
        personService.updatePersonRoom(id, login);
        return "200";
    }

    @PutMapping("/{id}/private")
    public String joinPrivateRoom(@PathVariable String login, @PathVariable Long id) throws Exception {
        personService.updatePersonPrivateRoom(id, login);
        return "200";
    }

    @PutMapping("/{id}/exit")
    public String exitRoom(@PathVariable String login, @PathVariable Long id) throws Exception {
        personService.updatePersonExitRoom(login, id);
        return "200";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {
        personService.deleteRoom(id);
        return "200";
    }
}
