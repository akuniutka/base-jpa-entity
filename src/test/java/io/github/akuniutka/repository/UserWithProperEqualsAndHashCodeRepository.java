package io.github.akuniutka.repository;

import io.github.akuniutka.model.UserWithProperEqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserWithProperEqualsAndHashCodeRepository
        extends JpaRepository<UserWithProperEqualsAndHashCode, UUID> {

}
