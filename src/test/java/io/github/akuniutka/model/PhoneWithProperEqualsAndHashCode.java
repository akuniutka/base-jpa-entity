package io.github.akuniutka.model;

import io.github.akuniutka.data.BaseHibernateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "phones_proper")
@Getter
@Setter
public class PhoneWithProperEqualsAndHashCode extends BaseHibernateEntity {

    private String number;
    private String type;
}
