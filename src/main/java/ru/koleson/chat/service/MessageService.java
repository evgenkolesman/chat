package ru.koleson.chat.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.koleson.chat.model.Message;
import ru.koleson.chat.repository.MessageRepository;

import java.rmi.ServerException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repo;

    public Message findById(Long id) throws Exception {
        if (repo.findById(id).isPresent()) {
        return repo.findById(id).get();
        } else throw new NotFoundException("400");
    }

    public List<Message> findAll() {
        return stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void createOrUpdate(Message message) throws Exception{
        if(message != null) {
        repo.save(message);
        } else throw  new ServerException("500");
    }

    public void deleteMessage(Long id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
        }
    }
}
