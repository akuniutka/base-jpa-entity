package io.github.akuniutka.model;

import io.github.akuniutka.BaseJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users_proper")
@Getter
@Setter
public class UserWithProperEqualsAndHashCode extends BaseJpaEntity {

    private String name;
    private int age;

    @OneToOne(fetch = FetchType.LAZY)
    private PhoneWithProperEqualsAndHashCode phone;
}
