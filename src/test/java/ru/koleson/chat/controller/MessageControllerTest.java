package ru.koleson.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        when(messageService.findById(message.getId())).thenReturn(message);

        String req = mapper.writer().writeValueAsString(message);
        mvc.perform(get("/person/1/room/1/1").contentType(MediaType.APPLICATION_JSON)
                .content(req)).andExpect(status().isOk());

    }

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
        when(roomService.findById(room.getId())).thenReturn(room);
        when(messageService.findAll()).thenReturn(List.of(message));

        String req = mapper.writer().writeValueAsString(message);
        mvc.perform(get("/person/1/room/1/").contentType(MediaType.APPLICATION_JSON)
                .content(req)).andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        when(personService.findAll()).thenReturn(List.of(person));
        when(roomService.findById(room.getId())).thenReturn(room);
        when(messageService.create(message)).thenReturn(message);

        String req = mapper.writer().writeValueAsString(message);
        mvc.perform(post("/person/1/room/1/").contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andDo(print())
                .andExpect(status().isOk());

        ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
        verify(messageService).create(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getId(), is(1L));
        MatcherAssert.assertThat(argument.getValue().getText(), is("Hello"));
    }

    @Test
    void update() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);

        Person person = Person.of("1","1","1","1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        when(personService.findAll()).thenReturn(List.of(person));
        when(roomService.findById(room.getId())).thenReturn(room);
        when(messageService.create(message)).thenReturn(message);
        message.setText("Hello1");
        when(messageService.update(message)).thenReturn(message);

        String req = mapper.writer().writeValueAsString(message);
        mvc.perform(put("/person/1/room/1/1").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());

        ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
        verify(messageService).update(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getText(), is("Hello1"));
    }

}