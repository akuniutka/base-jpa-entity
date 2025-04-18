package io.github.akuniutka;

import io.github.akuniutka.model.PhoneWithCustomEqualsAndHashCode;
import io.github.akuniutka.model.PhoneWithLombokEqualsAndHashCode;
import io.github.akuniutka.model.PhoneWithLombokEqualsAndHashCodeAdjusted;
import io.github.akuniutka.model.PhoneWithProperEqualsAndHashCode;
import io.github.akuniutka.model.UserWithDefaultEqualsAndHashCode;
import io.github.akuniutka.model.UserWithLombokEqualsAndHashCode;
import io.github.akuniutka.model.UserWithProperEqualsAndHashCode;
import io.github.akuniutka.repository.PhoneWithCustomEqualsAndHashCodeRepository;
import io.github.akuniutka.repository.PhoneWithLombokEqualsAndHashCodeAdjustedRepository;
import io.github.akuniutka.repository.PhoneWithLombokEqualsAndHashCodeRepository;
import io.github.akuniutka.repository.PhoneWithProperEqualsAndHashCodeRepository;
import io.github.akuniutka.repository.UserWithDefaultEqualsAndHashCodeRepository;
import io.github.akuniutka.repository.UserWithLombokEqualsAndHashCodeAdjustedRepository;
import io.github.akuniutka.repository.UserWithLombokEqualsAndHashCodeRepository;
import io.github.akuniutka.repository.UserWithProperEqualsAndHashCodeRepository;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootConfiguration
@EnableAutoConfiguration
@SpringBootTest
class ImplementationsComparisonTest {

    private final UUID userId = UUID.fromString("871bc130-0b36-49ae-b631-b298b95e9478");
    private final UUID phoneId = UUID.fromString("0749be04-2188-491b-b62c-83c717c69671");

    @Autowired
    private UserWithDefaultEqualsAndHashCodeRepository userDefaultRepository;

    @Autowired
    private UserWithLombokEqualsAndHashCodeRepository userLombokRepository;

    @Autowired
    private UserWithLombokEqualsAndHashCodeAdjustedRepository userLombokAdjustedRepository;

    @Autowired
    private UserWithProperEqualsAndHashCodeRepository userProperRepository;

    @Autowired
    private PhoneWithLombokEqualsAndHashCodeRepository phoneLombokRepository;

    @Autowired
    private PhoneWithLombokEqualsAndHashCodeAdjustedRepository phoneLombokAdjustedRepository;

    @Autowired
    private PhoneWithCustomEqualsAndHashCodeRepository phoneCustomRepository;

    @Autowired
    private PhoneWithProperEqualsAndHashCodeRepository phoneProperRepository;

    @Test
    void whenDefaultEqualsAndHashCode_ThenSameEntityInstancesFromDifferentTransactionsAreNotEqual() {

        final UserWithDefaultEqualsAndHashCode user = userDefaultRepository.findById(userId).orElseThrow();
        final List<UserWithDefaultEqualsAndHashCode> users = userDefaultRepository.findAll();

        assertFalse(users.contains(user));
    }

    @Test
    void whenProperEqualsAndHashCode_ThenSameEntityInstanceFromDifferentTransactionsAreEqual() {

        final UserWithProperEqualsAndHashCode user = userProperRepository.findById(userId).orElseThrow();
        final List<UserWithProperEqualsAndHashCode> users = userProperRepository.findAll();

        assertTrue(users.contains(user));
    }

    @Test
    void whenLombokEqualsAndHashCode_ThenSameEntityInstancesWithDifferentSecondaryFieldValuesAreNotEqual() {

        final PhoneWithLombokEqualsAndHashCode phone = phoneLombokRepository.findById(phoneId).orElseThrow();
        phone.setType("mobile");
        final List<PhoneWithLombokEqualsAndHashCode> phones = phoneLombokRepository.findAll();

        assertFalse(phones.contains(phone));
    }

    @Test
    void whenLombokEqualsAndHashCode_ThenLoadFieldsWithLazyInitialization() {

        final UserWithLombokEqualsAndHashCode user = userLombokRepository.findById(userId).orElseThrow();
        final List<UserWithLombokEqualsAndHashCode> users = userLombokRepository.findAll();

        assertThrows(LazyInitializationException.class, () -> users.contains(user));
    }

    @Test
    void whenProperEqualsAndHashCode_ThenSameEntityInstancesWithDifferentSecondaryFieldValuesAreEqual() {

        final PhoneWithProperEqualsAndHashCode phone = phoneProperRepository.findById(phoneId).orElseThrow();
        phone.setType("mobile");
        final List<PhoneWithProperEqualsAndHashCode> phones = phoneProperRepository.findAll();

        assertTrue(phones.contains(phone));
    }

    @Test
    void whenProperEqualsAndHashCode_ThenDoesNotLoadFieldsWithLazyInitialization() {

        final UserWithProperEqualsAndHashCode user = userProperRepository.findById(userId).orElseThrow();
        final List<UserWithProperEqualsAndHashCode> users = userProperRepository.findAll();

        assertTrue(users.contains(user));
        assertFalse(Hibernate.isInitialized(user.getPhone()));
    }

    @Test
    void whenLombokEqualsAndHashCodeAdjusted_ThenLoadFieldsForHibernateProxy() {

        final PhoneWithLombokEqualsAndHashCodeAdjusted phone =
                userLombokAdjustedRepository.findById(userId).orElseThrow().getPhone();
        final List<PhoneWithLombokEqualsAndHashCodeAdjusted> phones = phoneLombokAdjustedRepository.findAll();

        assertThrows(LazyInitializationException.class, () -> phones.contains(phone));
    }

    @Test
    void whenProperEqualsAndHashCode_ThenDoesNotLoadFieldsForHibernateProxy() {

        final PhoneWithProperEqualsAndHashCode phone = userProperRepository.findById(userId).orElseThrow().getPhone();
        final List<PhoneWithProperEqualsAndHashCode> phones = phoneProperRepository.findAll();

        assertTrue(phones.contains(phone));
    }

    @Test
    void whenCustomEqualsAndHashCodeButIdGeneratedByHibernate_ThenEntityNotEqualBeforeAndAfterPersisted() {

        final Set<PhoneWithCustomEqualsAndHashCode> phones = new HashSet<>();
        final PhoneWithCustomEqualsAndHashCode phone = new PhoneWithCustomEqualsAndHashCode();
        phone.setNumber("+123456789");
        phone.setType("office");
        phones.add(phone);
        phoneCustomRepository.save(phone);

        assertFalse(phones.contains(phone));
    }

    @Test
    void whenProperEqualsAndHashCode_ThenEntityEqualBeforeAndAfterPersisted() {

        final Set<PhoneWithProperEqualsAndHashCode> phones = new HashSet<>();
        final PhoneWithProperEqualsAndHashCode phone = new PhoneWithProperEqualsAndHashCode();
        phone.setNumber("+123456789");
        phone.setType("office");
        phones.add(phone);
        phoneProperRepository.save(phone);

        assertTrue(phones.contains(phone));
    }
}
