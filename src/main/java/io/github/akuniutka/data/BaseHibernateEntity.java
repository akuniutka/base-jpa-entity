package io.github.akuniutka.data;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseHibernateEntity {

    @Id
    private UUID id;

    protected BaseHibernateEntity() {
        this(Generators.timeBasedEpochGenerator().generate());
    }

    protected BaseHibernateEntity(final UUID id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
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
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }
}
