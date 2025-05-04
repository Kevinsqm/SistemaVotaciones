package api.sistemaVotaciones.generics;

import api.sistemaVotaciones.generics.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericRepository<Entity extends BaseEntity, Id extends Serializable> extends JpaRepository<Entity, Id> {
}
