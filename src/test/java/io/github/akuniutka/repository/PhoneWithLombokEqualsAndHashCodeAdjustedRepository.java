package io.github.akuniutka.repository;

import io.github.akuniutka.model.PhoneWithLombokEqualsAndHashCodeAdjusted;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneWithLombokEqualsAndHashCodeAdjustedRepository
        extends JpaRepository<PhoneWithLombokEqualsAndHashCodeAdjusted, UUID> {

}
