package ru.koleson.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koleson.chat.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
