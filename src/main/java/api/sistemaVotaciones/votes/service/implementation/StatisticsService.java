package api.sistemaVotaciones.votes.service.implementation;

import api.sistemaVotaciones.candidates.repository.CandidateRepository;
import api.sistemaVotaciones.voters.DTOs.CandidateStatistics;
import api.sistemaVotaciones.voters.repository.VoterRepository;
import api.sistemaVotaciones.votes.DTOs.VotingStatisticsDto;
import api.sistemaVotaciones.votes.service.interfaces.IStatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Service
public class StatisticsService implements IStatisticsService {

    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public StatisticsService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public VotingStatisticsDto getVotingStatistics() {
        VotingStatisticsDto votingStatisticsDto = new VotingStatisticsDto();
        var totalVoters = voterRepository.countVotersWhoHaveVoted();
        var candidatesStatistics = candidateRepository.findAll().stream()
                .map(c -> {
                    var candidateStatistics = new CandidateStatistics();
                    candidateStatistics.setCandidate(c.getName());
                    candidateStatistics.setTotal_votes(c.getVotes());
                    double percentage =  c.getVotes().doubleValue() / totalVoters.doubleValue() * 100;
                    BigDecimal bigDecimal = new BigDecimal(percentage).setScale(2, RoundingMode.HALF_UP);
                    candidateStatistics.setPercentage_of_votes(bigDecimal.doubleValue());
                    return candidateStatistics;
                }).toList();
        votingStatisticsDto.setVoters_Who_Have_Voted(totalVoters);
        votingStatisticsDto.setStatistics_per_Candidate(candidatesStatistics);
        return votingStatisticsDto;
    }

}
