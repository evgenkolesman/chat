package ru.koleson.chat.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.koleson.chat.model.Room;
import ru.koleson.chat.repository.RoomRepository;

import java.rmi.ServerException;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;

    public Room findById(Long id) throws Exception {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else throw new NotFoundException("404");
    }

    public void save(Room room) throws Exception{
        if (repository.findById(room.getId()).isEmpty()) {
            repository.save(room);
        } else throw new ServerException("404");
    }

    public void deleteRoom(Long id) {
        if(repository.existsById(id))
        repository.deleteById(id);
    }
}
