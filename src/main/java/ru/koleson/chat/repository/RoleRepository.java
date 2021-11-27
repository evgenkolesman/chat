package ru.koleson.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koleson.role.model.Roles;

public interface RoleRepository extends CrudRepository<Roles, String> {

    public Roles findRoleByName(String name);

    boolean existsByName(String name);
}
