package api.sistemaVotaciones.generics;

import api.sistemaVotaciones.generics.model.BaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface GenericMapper<DTORES, DTOREQ, DTOREQUPD, Entity extends BaseEntity> {


    Entity toEntity(DTOREQ dto);

    DTORES toDTO(Entity entity);

    Entity DtoReqUpdToEntity(DTOREQUPD dto);
}
