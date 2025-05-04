package api.sistemaVotaciones.users.repository;

import api.sistemaVotaciones.generics.GenericRepository;
import api.sistemaVotaciones.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
