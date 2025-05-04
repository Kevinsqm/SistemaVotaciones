package api.sistemaVotaciones.votes.DTOs;

import api.sistemaVotaciones.candidates.DTOs.CandidateDtoResponse;
import api.sistemaVotaciones.voters.DTOs.VoterDtoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class VoteDtoResponse {

    private Long id;

    @JsonIgnoreProperties({"id_card", "email", "has_voted"})
    private VoterDtoResponse voter;

    @JsonIgnoreProperties({"id_card", "party", "votes"})
    private CandidateDtoResponse candidate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VoterDtoResponse getVoter() {
        return voter;
    }

    public void setVoter(VoterDtoResponse voter) {
        this.voter = voter;
    }

    public CandidateDtoResponse getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateDtoResponse candidate) {
        this.candidate = candidate;
    }
}
