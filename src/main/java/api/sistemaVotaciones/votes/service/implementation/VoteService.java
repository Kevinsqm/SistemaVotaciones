package api.sistemaVotaciones.votes.service.implementation;

import api.sistemaVotaciones.candidates.mappers.CandidateMapper;
import api.sistemaVotaciones.candidates.model.Candidate;
import api.sistemaVotaciones.candidates.service.interfaces.ICandidateService;
import api.sistemaVotaciones.exceptions.EntityNotFoundException;
import api.sistemaVotaciones.exceptions.InvalidEntityException;
import api.sistemaVotaciones.voters.mappers.VoterMapper;
import api.sistemaVotaciones.voters.model.Voter;
import api.sistemaVotaciones.voters.service.interfaces.IVoterService;
import api.sistemaVotaciones.votes.DTOs.VoteDtoRequest;
import api.sistemaVotaciones.votes.DTOs.VoteDtoResponse;
import api.sistemaVotaciones.votes.mappers.VoteMapper;
import api.sistemaVotaciones.votes.model.Vote;
import api.sistemaVotaciones.votes.repository.VoteRepository;
import api.sistemaVotaciones.votes.service.interfaces.IVoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteService implements IVoteService {

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final ICandidateService candidateService;
    private final IVoterService voterService;
    private final CandidateMapper candidateMapper;
    private final VoterMapper voterMapper;

    public VoteService(VoteRepository voteRepository, VoteMapper voteMapper, ICandidateService candidateService, IVoterService voterService, CandidateMapper candidateMapper, VoterMapper voterMapper) {
        this.voteRepository = voteRepository;
        this.voteMapper = voteMapper;
        this.candidateService = candidateService;
        this.voterService = voterService;
        this.candidateMapper = candidateMapper;
        this.voterMapper = voterMapper;
    }

    @Override
    public Page<VoteDtoResponse> getAll(Pageable pageable) {
        return voteRepository.findAll(pageable)
                .map(voteMapper::toVoteDtoResponse);
    }

    @Override
    @Transactional
    public void save(VoteDtoRequest voteDto) {
        Voter voter = getValidatedVoter(voteDto.getVoter_id());
        Candidate candidate = getValidatedCandidate(voteDto.getCandidate_id());
        createAndSaveVote(voter, candidate);
        voterService.updateVoterStatus(voter.getId());
        candidateService.increaseVotes(candidate.getId());
    }

    private Voter getValidatedVoter(Long voterId){
        if(!voterService.existById(voterId))
            throw new EntityNotFoundException("voter with id " + voterId + " not found");
        Voter voter = voterMapper.dtoResponseToEntity(voterService.getById(voterId));
        if(voter.getHasVoted())
            throw new InvalidEntityException("The voter has already voted");
        return voter;
    }

    private Candidate getValidatedCandidate(Long candidateId){
        if(!candidateService.existById(candidateId))
            throw new EntityNotFoundException("candidate with id " + candidateId + " not found");
        return candidateMapper.dtoResponseToEntity(candidateService.getById(candidateId));
    }

    private void createAndSaveVote(Voter voter, Candidate candidate){
        var vote = new Vote();
        vote.setCandidate(candidate);
        vote.setVoter(voter);
        voteRepository.save(vote);
    }
}
