package api.sistemaVotaciones.voters.mappers;

import api.sistemaVotaciones.generics.GenericMapper;
import api.sistemaVotaciones.voters.DTOs.UpdateVoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoResponse;
import api.sistemaVotaciones.voters.model.Voter;
import api.sistemaVotaciones.votes.DTOs.VoteDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoterMapper extends GenericMapper<VoterDtoResponse, VoterDtoRequest, UpdateVoterDtoRequest, Voter> {

    @Mapping(source = "id_card", target = "idCard")
    @Override
    Voter toEntity(VoterDtoRequest dto);

    @Mapping(source = "idCard", target = "id_card")
    @Mapping(source = "hasVoted", target = "has_voted")
    @Override
    VoterDtoResponse toDTO(Voter entity);

    @Mapping(source = "id_card", target = "idCard")
    @Mapping(source = "has_voted", target = "hasVoted")
    Voter dtoResponseToEntity(VoterDtoResponse voterDto);

}
