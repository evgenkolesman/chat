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
import ru.koleson.chat.repository.RoleRepository;
import ru.koleson.chat.service.PersonService;
import ru.koleson.chat.service.RoleService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class RoleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    void findAllRole() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Role role = new Role("A");
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        when(roleService.findAll()).thenReturn(List.of(role));
        when(personService.findAll()).thenReturn(List.of(person));
        String req = mapper.writer().writeValueAsString(role);
        mvc.perform(get("/person/role").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isOk());
    }

    @Test
    void findByNameRole() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Role role = new Role("Z");
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        when(roleRepository.existsByName(role.getName())).thenReturn(true);
        when(roleService.findByRole(role.getName())).thenReturn(role);
        when(personService.findAll()).thenReturn(List.of(person));

        String req = mapper.writer().writeValueAsString(role);
        mvc.perform(get("/person/role/Z").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createRole() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        when(roleService.create(role)).thenReturn("200");
        when(personService.findAll()).thenReturn(List.of(person));
        String req = mapper.writer().writeValueAsString(role);
        mvc.perform(post("/person/role").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateRole() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        when(roleService.update(role)).thenReturn("200");
        when(personService.findAll()).thenReturn(List.of(person));
        String req = mapper.writer().writeValueAsString(role);
        mvc.perform(put("/person/role").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteRole() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Message message = Message.of("Hello");
        message.setId(1L);
        Person person = Person.of("1", "1", "1", "1");
        Room room = new Room();
        room.setId(1L);
        person.setRooms(List.of(room));
        Role role = new Role("Z");
        when(roleService.update(role)).thenReturn("200");
        when(personService.findAll()).thenReturn(List.of(person));
        String req = mapper.writer().writeValueAsString(role);
        mvc.perform(delete("/person/role/Z").contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andDo(print())
                .andExpect(status().isOk());
    }
}