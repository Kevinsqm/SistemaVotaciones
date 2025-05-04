package api.sistemaVotaciones.votes.controller;

import api.sistemaVotaciones.votes.DTOs.VoteDtoRequest;
import api.sistemaVotaciones.votes.DTOs.VoteDtoResponse;
import api.sistemaVotaciones.votes.DTOs.VotingStatisticsDto;
import api.sistemaVotaciones.votes.service.interfaces.IStatisticsService;
import api.sistemaVotaciones.votes.service.interfaces.IVoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

    private final IVoteService voteService;
    private final IStatisticsService statisticsService;

    public VoteController(IVoteService voteService, IStatisticsService statisticsService) {
        this.voteService = voteService;
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ResponseEntity<Page<VoteDtoResponse>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(voteService.getAll(pageable));
    }

    @GetMapping("/statistics")
    public ResponseEntity<VotingStatisticsDto> statistics(){
        return ResponseEntity.ok(statisticsService.getVotingStatistics());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VoteDtoRequest voteDto){
        voteService.save(voteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
