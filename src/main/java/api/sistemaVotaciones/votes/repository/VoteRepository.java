package api.sistemaVotaciones.votes.repository;

import api.sistemaVotaciones.generics.GenericRepository;
import api.sistemaVotaciones.votes.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
