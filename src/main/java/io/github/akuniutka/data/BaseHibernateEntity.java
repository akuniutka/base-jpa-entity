package io.github.akuniutka.data;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.domain.Persistable;

import java.util.Objects;
import java.util.UUID;

/**
 * A base class for Hibernate-managed JPA entities with proper
 * implementations of equals() and hashCode(). They allow to
 * compare entities and use entities in hash-based collections
 * the same way before and after entities persisted. They also
 * prevent unintentional proxy initialization when an entity
 * is represented by a Hibernate proxy.
 *
 * @author Andrei Kuniutka
 * @version 1.0
 */
@MappedSuperclass
public abstract class BaseHibernateEntity implements Persistable<UUID> {

    @Id
    private UUID id;

    /*
     * Implement Persistable<ID>.isNew() to help Spring to identify whether
     * the entity is new or was persisted already. Thus save one SELECT-query
     * to the database when saving the entity for the first time.
     */
    @Transient
    private boolean isNew;

    /*
     * Generate id for the entity and do not leave it to Hibernate
     * as there might be a need to put the entity into a collection
     * before persisting it (for example, when persisting several
     * entities in a batch). In order equals() and hashCode() to work
     * identically before and after the entity persisted, assign id
     * to the entity on its creation.
     */
    protected BaseHibernateEntity() {
        this(Generators.timeBasedEpochGenerator().generate());
    }

    protected BaseHibernateEntity(final UUID id) {
        Objects.requireNonNull(id);
        this.id = id;
        this.isNew = true;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PrePersist
    void trackNotNew() {
        this.isNew = false;
    }

    /*
     * Make equals() final in order not to trigger proxy initialization
     * when the entity is represented by such a proxy. Hibernate
     * initializes the proxy (and goes to database) when there is a call
     * to a non-final method of the proxy (with exception of calls to
     * getId()).
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        /*
         * In order to check whether the other entity is of the same
         * class, get the actual underlying class when the entity
         * (or the other entity) is represented by a Hibernate proxy.
         */
        final Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        final Class<?> objEffectiveClass = obj instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : obj.getClass();
        if (thisEffectiveClass != objEffectiveClass) {
            return false;
        }
        final BaseHibernateEntity entity = (BaseHibernateEntity) obj;
        /*
         * Compare instances by the primary key which is the unique
         * identifier of entity's representation in database -- a row.
         * When instances have the same id they are instances of the
         * same entity.
         */
        return Objects.equals(getId(), entity.getId());
    }

    /*
     * Make hashCode() final in order not to trigger proxy
     * initialization when the entity is represented by such
     * a proxy. For details see a comment to equals().
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }
}
