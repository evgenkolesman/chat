package ru.koleson.chat.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    private String login;

    private String name;

    private String surname;

    @NotNull
    private String password;

    @ManyToOne
    @JoinColumn
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messages;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

    public static Person of(String login, String name, String surname, String password) {
        Person person = new Person();
        person.login = login;
        person.surname = surname;
        person.name = name;
        person.password = password;
        return person;
    }
}
