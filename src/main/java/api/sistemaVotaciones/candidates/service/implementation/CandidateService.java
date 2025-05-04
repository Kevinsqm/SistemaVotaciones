package api.sistemaVotaciones.candidates.service.implementation;

import api.sistemaVotaciones.candidates.DTOs.CandidateDtoRequest;
import api.sistemaVotaciones.candidates.DTOs.CandidateDtoResponse;
import api.sistemaVotaciones.candidates.DTOs.UpdateCandidateDtoRequest;
import api.sistemaVotaciones.candidates.mappers.CandidateMapper;
import api.sistemaVotaciones.candidates.model.Candidate;
import api.sistemaVotaciones.candidates.repository.CandidateRepository;
import api.sistemaVotaciones.candidates.service.interfaces.ICandidateService;
import api.sistemaVotaciones.exceptions.EntityNotFoundException;
import api.sistemaVotaciones.exceptions.InvalidEntityException;
import api.sistemaVotaciones.generics.GenericMapper;
import api.sistemaVotaciones.generics.GenericRepository;
import api.sistemaVotaciones.generics.service.implementation.GenericService;
import api.sistemaVotaciones.validations.PersonValidationService;
import api.sistemaVotaciones.voters.service.interfaces.IVoterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CandidateService extends GenericService<CandidateDtoResponse, Candidate, Long, CandidateMapper> implements ICandidateService {

    private final CandidateRepository repository;
    private final CandidateMapper mapper;
    private final PersonValidationService personValidationService;

    public CandidateService(GenericRepository<Candidate, Long> genericRepository,
                            CandidateMapper mapper,
                            CandidateRepository repository,
                            PersonValidationService personValidationService) {

        super(genericRepository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.personValidationService = personValidationService;
    }

    @Transactional
    @Override
    public Candidate save(CandidateDtoRequest candidateDto) {
        Candidate candidate = mapper.toEntity(candidateDto);
        if (personValidationService.isCandidateRegisteredAsVoter(candidate.getIdCard()))
            throw new InvalidEntityException("The candidate is already registered as a voter");
        if (existsByIdCard(candidate.getIdCard()))
            throw new InvalidEntityException("There is already a candidate registered with ID Card " + candidate.getIdCard());
        return repository.save(candidate);
    }

    @Transactional
    @Override
    public void update(UpdateCandidateDtoRequest candidateDto, Long id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException("Candidate not found");
        var candidate = mapper.DtoReqUpdToEntity(candidateDto);
        candidate.setId(id);
        repository.save(candidate);
    }

    @Override
    public boolean existsByIdCard(Long idCard) {
        return repository.findByIdCard(idCard).isPresent();
    }

    @Transactional
    @Override
    public void increaseVotes(Long id) {
        var candidate = repository.findById(id).orElseThrow();
        candidate.setVotes(candidate.getVotes() + 1);
        repository.save(candidate);
    }
}
