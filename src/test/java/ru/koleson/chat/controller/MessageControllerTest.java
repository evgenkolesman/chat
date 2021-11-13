package ru.koleson.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.koleson.chat.model.Message;
import ru.koleson.chat.model.Person;
import ru.koleson.chat.model.Room;
import ru.koleson.chat.repository.MessageRepository;
import ru.koleson.chat.repository.PersonRepository;
import ru.koleson.chat.repository.RoomRepository;
import ru.koleson.chat.service.MessageService;
import ru.koleson.chat.service.PersonService;
import ru.koleson.chat.service.RoomService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageService messageService;

    @MockBean
    private MessageRepository repo;

    @MockBean
    private PersonController controller;

    @MockBean
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    @Ignore
    void findMessageTestWithOutPerson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        when(personService.findByLogin(person.getLogin())).thenReturn(person);
        when(roomService.findById(room.getId())).thenReturn(room);
        when(messageService.findAll()).thenReturn(List.of(message));

        String req = mapper.writer().writeValueAsString(message);
        mvc.perform(get("/person/1/room/1/)").contentType(MediaType.APPLICATION_JSON)
                .content(req)).andExpect(status().isOk());


    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}