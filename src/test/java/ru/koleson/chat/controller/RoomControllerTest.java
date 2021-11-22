package ru.koleson.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.koleson.chat.model.Message;
import ru.koleson.chat.model.Person;
import ru.koleson.chat.model.Role;
import ru.koleson.chat.model.Room;
import ru.koleson.chat.service.PersonService;
import ru.koleson.chat.service.RoomService;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomService roomService;

    @MockBean
    private PersonService personService;

    @Test
    void createRoomTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        when(personService.findByLogin(person.getLogin())).thenReturn(person);
        doNothing().when(personService).updatePersonRoom(0L, person.getLogin());
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(post("/person/1/room/").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createPrivateRoomTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        person.setRole(role);
        when(personService.findByLogin(person.getLogin())).thenReturn(person);
        doNothing().when(personService).updatePersonRoom(0L, person.getLogin());
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(post("/person/1/room/private/").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void joinRoomTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        person.setRole(role);
        when(personService.findByLogin(person.getLogin())).thenReturn(person);
        doNothing().when(personService).updatePersonRoom(0L, person.getLogin());
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(put("/person/1/room/1").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void joinPrivateRoomTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        person.setRole(role);
        when(personService.findByLogin(person.getLogin())).thenReturn(person);
        doNothing().when(personService).updatePersonRoom(0L, person.getLogin());
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(put("/person/1/room/1/private").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void exitRoom() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        person.setRole(role);
        when(personService.findByLogin(person.getLogin())).thenReturn(person);
        doNothing().when(personService).updatePersonExitRoom(person.getLogin(), 1L);
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(put("/person/1/room/1/exit").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteRoom() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        person.setRole(role);
        when(personService.findByLogin(person.getLogin())).thenReturn(person);
        doNothing().when(personService).deleteRoom(1L);
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(delete("/person/1/room/1/").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }
}