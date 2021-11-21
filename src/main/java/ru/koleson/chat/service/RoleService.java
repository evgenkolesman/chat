package ru.koleson.chat.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import ru.koleson.chat.model.Role;
import ru.koleson.chat.repository.RoleRepository;

import java.rmi.ServerException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repo;

    @NotFound
    public List<Role> findAll() {
        return new LinkedList<>(stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList()));
    }

    @NotFound
    public Role findByRole(String name) {
        Role role = null;
        if (repo.existsByName(name))
            role = repo.findRoleByName(name);
        return role;
    }

    public String create(Role role) throws ServerException {
        if (repo.existsByName(role.getName())) {
            repo.save(role);
            return "200";
        } else throw new ServerException("Such role exists!");
    }

    @NotFound
    public String update(Role role) {
        if (repo.existsByName(role.getName()))
            repo.save(role);
        return "200";
    }

    @NotFound
    public String delete(Role role) {
        if (repo.existsByName(role.getName()))
            repo.delete(role);
        return "200";
    }
}
