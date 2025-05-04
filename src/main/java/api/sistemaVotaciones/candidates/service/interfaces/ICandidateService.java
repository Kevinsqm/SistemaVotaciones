package api.sistemaVotaciones.candidates.service.interfaces;

import api.sistemaVotaciones.candidates.DTOs.CandidateDtoRequest;
import api.sistemaVotaciones.candidates.DTOs.CandidateDtoResponse;
import api.sistemaVotaciones.candidates.DTOs.UpdateCandidateDtoRequest;
import api.sistemaVotaciones.candidates.model.Candidate;
import api.sistemaVotaciones.generics.service.interfaces.IGenericService;

public interface ICandidateService extends IGenericService<CandidateDtoResponse, Candidate, Long> {

    public Candidate save(CandidateDtoRequest candidateDto);
    public void update(UpdateCandidateDtoRequest candidateDto, Long id);
    public boolean existsByIdCard(Long idCard);
    public void increaseVotes(Long id);
}
