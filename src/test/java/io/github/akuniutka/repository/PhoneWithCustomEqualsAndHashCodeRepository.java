package io.github.akuniutka.repository;

import io.github.akuniutka.model.PhoneWithCustomEqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneWithCustomEqualsAndHashCodeRepository
        extends JpaRepository<PhoneWithCustomEqualsAndHashCode, UUID> {

}
