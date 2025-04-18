package io.github.akuniutka.repository;

import io.github.akuniutka.model.UserWithLombokEqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserWithLombokEqualsAndHashCodeRepository
        extends JpaRepository<UserWithLombokEqualsAndHashCode, UUID> {

}
