package api.sistemaVotaciones.votes.mappers;

import api.sistemaVotaciones.candidates.mappers.CandidateMapper;
import api.sistemaVotaciones.voters.mappers.VoterMapper;
import api.sistemaVotaciones.votes.DTOs.VoteDtoResponse;
import api.sistemaVotaciones.votes.model.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VoterMapper.class, CandidateMapper.class})
public interface VoteMapper {

    VoteDtoResponse toVoteDtoResponse(Vote vote);
}
