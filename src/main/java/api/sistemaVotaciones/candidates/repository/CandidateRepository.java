package api.sistemaVotaciones.candidates.repository;

import api.sistemaVotaciones.candidates.model.Candidate;
import api.sistemaVotaciones.generics.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends GenericRepository<Candidate, Long> {

    Optional<Candidate>  findByIdCard(Long idCard);
}
