package ru.koleson.chat.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.koleson.chat.model.Person;
import ru.koleson.chat.model.Room;
import ru.koleson.chat.repository.PersonRepository;

import java.rmi.ServerException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

/**
 * Продумать логику когда новая комната создается
 */
@Service
@RequiredArgsConstructor
public class PersonService {

    private final RoomService serviceRoom;
    private final PersonRepository repoPerson;

    public Person findByLogin(String login) throws NotFoundException {
        if (repoPerson.findByLogin(login) != null) {
            return repoPerson.findByLogin(login);
        } else throw new NotFoundException("404");
    }

    public List<Person> findAll() {
        return stream(
                this.repoPerson.findAll().spliterator(), false)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void createPerson(Person person) throws Exception {
        if (repoPerson.findByLogin(person.getLogin()) == null) {
            repoPerson.save(person);
        } else throw new ServerException("500");
    }

    public void updatePerson(Person person) throws Exception {
        if (repoPerson.findByLogin(person.getLogin()) != null) {
            repoPerson.save(person);
        } else throw new ServerException("500");
    }

    public void deletePerson(String login) throws Exception{
        if (repoPerson.findByLogin(login) != null) {
            repoPerson.delete(findByLogin(login));
        } else throw new NotFoundException("404");
    }

    /**
     * надо продумать как правильно сохранить room, чтобы присвоить им id
     * и вернуть его в update
     * @param id
     * @param login
     * @throws Exception
     */
    public void updatePersonPrivateRoom(Long id, String login) throws Exception {
        Room room = serviceRoom.findById(id);
        Person person = repoPerson.findByLogin(login);
        room.setPrivateRoom(true);
        List <Room> roomList = repoPerson.findByLogin(login).getRooms();
        roomList.add(room);
        person.setRooms(roomList);
        repoPerson.save(person);
    }

    public void updatePersonRoom(Long id, String login) throws Exception {
        Room room;
        if (id == 0) {
            room = new Room();
            serviceRoom.save(room);
        } else {
        room = serviceRoom.findById(id); }
        Person person = repoPerson.findByLogin(login);
        List <Room> roomList = repoPerson.findByLogin(login).getRooms();
        roomList.add(room);
        person.setRooms(roomList);
        repoPerson.save(person);
    }

    public void updatePersonExitRoom(String login, Long id) throws Exception {
        Room room = serviceRoom.findById(id);
        Person person = repoPerson.findByLogin(login);
        List <Room> roomList = repoPerson.findByLogin(login).getRooms();
        roomList.remove(room);
        person.setRooms(roomList);
        repoPerson.save(person);
    }
}
