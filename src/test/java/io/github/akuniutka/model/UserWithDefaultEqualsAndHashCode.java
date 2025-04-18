package io.github.akuniutka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users_default")
@Getter
@Setter
public class UserWithDefaultEqualsAndHashCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private int age;

    @OneToOne(fetch = FetchType.LAZY)
    private PhoneWithDefaultEqualsAndHashCode phone;
}
