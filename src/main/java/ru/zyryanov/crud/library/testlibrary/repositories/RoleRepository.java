package ru.zyryanov.crud.library.testlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyryanov.crud.library.testlibrary.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
