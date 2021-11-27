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
import ru.koleson.chat.model.Room;
import ru.koleson.chat.repository.PersonRepository;
import ru.koleson.chat.service.PersonService;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;


    @Test
    void findAll() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        when(personService.findAll()).thenReturn(List.of(person));

        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(get("/person/all").contentType(MediaType.APPLICATION_JSON)
                .content(req)).andExpect(status().isOk());
    }

    @Test
    void findByLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        when(personService.findByLogin(person.getLogin())).thenReturn(person);


        String req = mapper.writer().writeValueAsString(message);
        mvc.perform(get("/person/1").contentType(MediaType.APPLICATION_JSON)
                .content(req)).andExpect(status().isOk());
    }

    /**
     * Use security model UserController#signUp
     * @throws Exception
     */
    @Deprecated
    @Test
    void create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        doNothing().when(personService).createPerson(person);
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(post("/person/reg/1").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        doNothing().when(personService).updatePerson(person);
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(put("/person/1").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        doNothing().when(personService).deletePerson(person.getLogin());
        String req = mapper.writer().writeValueAsString(person);
        mvc.perform(delete("/person/1").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }
}