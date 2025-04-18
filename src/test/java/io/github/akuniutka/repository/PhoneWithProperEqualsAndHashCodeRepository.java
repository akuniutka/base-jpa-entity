package io.github.akuniutka.repository;

import io.github.akuniutka.model.PhoneWithProperEqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneWithProperEqualsAndHashCodeRepository
        extends JpaRepository<PhoneWithProperEqualsAndHashCode, UUID> {

}
