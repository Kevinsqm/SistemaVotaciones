package api.sistemaVotaciones.voters.repository;

import api.sistemaVotaciones.generics.GenericRepository;
import api.sistemaVotaciones.voters.model.Voter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepository extends GenericRepository<Voter, Long> {

    Optional<Voter> findByIdCard(Long idCard);

    Optional<Voter> findByEmail(String email);

    @Query("SELECT COUNT(v) FROM Voter v WHERE v.hasVoted = true")
    Long countVotersWhoHaveVoted();

}
