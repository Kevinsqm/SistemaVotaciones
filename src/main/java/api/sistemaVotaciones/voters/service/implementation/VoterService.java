package api.sistemaVotaciones.voters.service.implementation;

import api.sistemaVotaciones.exceptions.EntityNotFoundException;
import api.sistemaVotaciones.exceptions.InvalidEntityException;
import api.sistemaVotaciones.generics.GenericRepository;
import api.sistemaVotaciones.generics.service.implementation.GenericService;
import api.sistemaVotaciones.validations.PersonValidationService;
import api.sistemaVotaciones.voters.DTOs.UpdateVoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoResponse;
import api.sistemaVotaciones.voters.mappers.VoterMapper;
import api.sistemaVotaciones.voters.model.Voter;
import api.sistemaVotaciones.voters.repository.VoterRepository;
import api.sistemaVotaciones.voters.service.interfaces.IVoterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoterService extends GenericService<VoterDtoResponse, Voter, Long, VoterMapper> implements IVoterService {

    private final VoterRepository repository;
    private final VoterMapper mapper;
    private final PersonValidationService personValidationService;

    protected VoterService(GenericRepository<Voter, Long> genericRepository,
                           VoterMapper mapper,
                           VoterRepository repository,
                           PersonValidationService personValidationService) {

        super(genericRepository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.personValidationService = personValidationService;
    }

    @Override
    @Transactional
    public Voter save(VoterDtoRequest voterDto) {
        Voter voter = mapper.toEntity(voterDto);
        if (personValidationService.isVoterRegisteredAsCandidate(voter.getIdCard()))
            throw new InvalidEntityException("The voter is already registered as a candidate");
        if (existsByIdCard(voter.getIdCard()))
            throw new InvalidEntityException("There is already a voter registered with this ID Card");
        if (existByEmail(voter.getEmail()))
            throw new InvalidEntityException("There is already a voter registered with this email");
        return repository.save(voter);
    }

    @Transactional
    @Override
    public void update(UpdateVoterDtoRequest voterDto, Long id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException("Voter not found");
        var entity = mapper.DtoReqUpdToEntity(voterDto);
        entity.setId(id);
        repository.save(entity);
    }

    @Override
    public boolean existsByIdCard(Long idCard) {
        return repository.findByIdCard(idCard).isPresent();
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    @Transactional
    @Override
    public void updateVoterStatus(Long id) {
        var voter = repository.findById(id).orElseThrow();
        voter.setHasVoted(true);
        repository.save(voter);
    }


}
