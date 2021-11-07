package ru.koleson.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koleson.chat.model.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
