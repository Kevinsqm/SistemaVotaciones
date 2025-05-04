package api.sistemaVotaciones.candidates.mappers;

import api.sistemaVotaciones.candidates.DTOs.CandidateDtoRequest;
import api.sistemaVotaciones.candidates.DTOs.CandidateDtoResponse;
import api.sistemaVotaciones.candidates.DTOs.UpdateCandidateDtoRequest;
import api.sistemaVotaciones.candidates.model.Candidate;
import api.sistemaVotaciones.generics.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateMapper extends GenericMapper<CandidateDtoResponse, CandidateDtoRequest, UpdateCandidateDtoRequest, Candidate> {

    @Mapping(source = "id_card", target = "idCard")
    @Override
    Candidate toEntity(CandidateDtoRequest dto);

    @Mapping(source = "idCard", target = "id_card")
    @Override
    CandidateDtoResponse toDTO(Candidate entity);

    @Mapping(source = "id_card", target = "idCard")
    Candidate dtoResponseToEntity(CandidateDtoResponse dtoResponse);

}
