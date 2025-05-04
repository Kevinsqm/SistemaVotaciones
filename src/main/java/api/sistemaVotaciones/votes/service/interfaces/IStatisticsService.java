package api.sistemaVotaciones.votes.service.interfaces;

import api.sistemaVotaciones.votes.DTOs.VotingStatisticsDto;

public interface IStatisticsService {

    VotingStatisticsDto getVotingStatistics();
}
