package api.sistemaVotaciones.votes.DTOs;

import api.sistemaVotaciones.voters.DTOs.CandidateStatistics;

import java.util.List;

public class VotingStatisticsDto {

    private Long Voters_Who_Have_Voted;
    private List<CandidateStatistics> statistics_per_Candidate;

    public Long getVoters_Who_Have_Voted() {
        return Voters_Who_Have_Voted;
    }

    public void setVoters_Who_Have_Voted(Long voters_Who_Have_Voted) {
        Voters_Who_Have_Voted = voters_Who_Have_Voted;
    }

    public List<CandidateStatistics> getStatistics_per_Candidate() {
        return statistics_per_Candidate;
    }

    public void setStatistics_per_Candidate(List<CandidateStatistics> statistics_per_Candidate) {
        this.statistics_per_Candidate = statistics_per_Candidate;
    }
}
