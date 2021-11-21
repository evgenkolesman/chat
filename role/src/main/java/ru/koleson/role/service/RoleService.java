package ru.koleson.role.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import ru.koleson.role.model.Roles;
import ru.koleson.role.repository.RoleRepository;

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
    public List<Roles> findAll() {
        return stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toCollection(LinkedList :: new));
    }

    @NotFound
    public Roles findByRole(String name) {
        Roles role = null;
        if (repo.existsByName(name))
            role = repo.findRoleByName(name);
        return role;
    }

    public String create(Roles role) throws ServerException {
        if (repo.existsByName(role.getName())) {
            repo.save(role);
            return "200";
        } else throw new ServerException("Such role exists!");
    }

    @NotFound
    public String update(Roles role) {
        if (repo.existsByName(role.getName()))
            repo.save(role);
        return "200";
    }

    @NotFound
    public String delete(Roles role) {
        if (repo.existsByName(role.getName()))
            repo.delete(role);
        return "200";
    }
}
