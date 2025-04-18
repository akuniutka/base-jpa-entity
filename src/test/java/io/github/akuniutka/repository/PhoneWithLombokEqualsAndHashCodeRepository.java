package io.github.akuniutka.repository;

import io.github.akuniutka.model.PhoneWithLombokEqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneWithLombokEqualsAndHashCodeRepository
        extends JpaRepository<PhoneWithLombokEqualsAndHashCode, UUID> {

}
