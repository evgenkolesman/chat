package ru.koleson.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Timestamp data;

    private Message of(String text) {
        Message message = new Message();
        message.text = text;
        message.data = new Timestamp(System.currentTimeMillis());
        return message;
    }
}
