package api.sistemaVotaciones.votes.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VoteDtoRequest {

    @NotNull(message = "The voter_id field cannot be null")
    private Long voter_id;

    @NotNull(message = "The candidate_id field cannot be null")
    private Long candidate_id;

    public Long getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(Long voter_id) {
        this.voter_id = voter_id;
    }

    public Long getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(Long candidate_id) {
        this.candidate_id = candidate_id;
    }
}
