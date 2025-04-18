package io.github.akuniutka.repository;

import io.github.akuniutka.model.UserWithDefaultEqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserWithDefaultEqualsAndHashCodeRepository
        extends JpaRepository<UserWithDefaultEqualsAndHashCode, UUID> {

}
