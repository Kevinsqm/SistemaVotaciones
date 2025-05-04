package api.sistemaVotaciones.generics.service.implementation;

import api.sistemaVotaciones.exceptions.EntityNotFoundException;
import api.sistemaVotaciones.generics.GenericMapper;
import api.sistemaVotaciones.generics.GenericRepository;
import api.sistemaVotaciones.generics.model.BaseEntity;
import api.sistemaVotaciones.generics.service.interfaces.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


public abstract class GenericService<DTO, Entity extends BaseEntity, Id extends Serializable, MPP extends GenericMapper<DTO,?,?, Entity>> implements IGenericService<DTO ,Entity, Id> {

    private final GenericRepository<Entity, Id> genericRepository;
    private final MPP mapper;

    public GenericService(GenericRepository<Entity, Id> genericRepository, MPP mapper) {
        this.genericRepository = genericRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<DTO> getAll(Pageable pageable) {
        return this.genericRepository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Override
    public DTO getById(Id id) {
        return this.genericRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    @Transactional
    public void delete(Id id) {
        if (genericRepository.existsById(id))
            this.genericRepository.deleteById(id);
        else
            throw new EntityNotFoundException("Entity not found");
    }

    public boolean existById(Id id){
        return genericRepository.existsById(id);
    }
}
