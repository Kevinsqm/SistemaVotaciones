package api.sistemaVotaciones.votes.service.interfaces;

import api.sistemaVotaciones.votes.DTOs.VoteDtoRequest;
import api.sistemaVotaciones.votes.DTOs.VoteDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVoteService {

    Page<VoteDtoResponse> getAll(Pageable pageable);
    void save(VoteDtoRequest voteDto);

}
