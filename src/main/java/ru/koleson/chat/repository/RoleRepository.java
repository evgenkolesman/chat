package ru.koleson.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.koleson.chat.model.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

    public Role findRoleByName(String name);

    boolean existsByName(String name);
}
