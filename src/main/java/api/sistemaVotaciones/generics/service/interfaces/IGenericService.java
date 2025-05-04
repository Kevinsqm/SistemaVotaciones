package api.sistemaVotaciones.generics.service.interfaces;

import api.sistemaVotaciones.generics.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IGenericService<DTO, Entity extends BaseEntity, Id extends Serializable> {

    public Page<DTO> getAll(Pageable pageable);
    public DTO getById(Id id);
    public void delete(Id id);
    public boolean existById(Id id);

}
