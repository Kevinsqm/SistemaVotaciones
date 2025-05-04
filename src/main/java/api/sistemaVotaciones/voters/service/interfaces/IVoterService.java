package api.sistemaVotaciones.voters.service.interfaces;

import api.sistemaVotaciones.generics.service.interfaces.IGenericService;
import api.sistemaVotaciones.voters.DTOs.UpdateVoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoResponse;
import api.sistemaVotaciones.voters.model.Voter;

public interface IVoterService extends IGenericService<VoterDtoResponse, Voter, Long> {

    public Voter save(VoterDtoRequest voterDto);
    public void update(UpdateVoterDtoRequest voterDto, Long id);
    public boolean existsByIdCard(Long idCard);
    public boolean existByEmail(String email);
    public void updateVoterStatus(Long id);
}
