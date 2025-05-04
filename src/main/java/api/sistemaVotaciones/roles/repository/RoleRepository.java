package api.sistemaVotaciones.roles.repository;

import api.sistemaVotaciones.roles.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
