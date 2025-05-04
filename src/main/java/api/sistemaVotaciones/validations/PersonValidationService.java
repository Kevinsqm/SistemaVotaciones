package api.sistemaVotaciones.validations;

import api.sistemaVotaciones.candidates.repository.CandidateRepository;
import api.sistemaVotaciones.voters.repository.VoterRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonValidationService {

    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public PersonValidationService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public boolean isVoterRegisteredAsCandidate(Long idCard) {
        return candidateRepository.findByIdCard(idCard).isPresent();
    }

    public boolean isCandidateRegisteredAsVoter(Long idCard) {
        return voterRepository.findByIdCard(idCard).isPresent();
    }

}
