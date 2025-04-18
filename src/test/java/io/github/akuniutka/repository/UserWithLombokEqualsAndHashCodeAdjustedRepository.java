package io.github.akuniutka.repository;

import io.github.akuniutka.model.UserWithLombokEqualsAndHashCodeAdjusted;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserWithLombokEqualsAndHashCodeAdjustedRepository
        extends JpaRepository<UserWithLombokEqualsAndHashCodeAdjusted, UUID> {

}
