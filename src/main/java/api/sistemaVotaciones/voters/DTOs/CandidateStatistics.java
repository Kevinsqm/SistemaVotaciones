package api.sistemaVotaciones.voters.DTOs;

public class CandidateStatistics {

    private String candidate;
    private Integer total_votes;
    private Double percentage_of_votes;

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public Integer getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(Integer total_votes) {
        this.total_votes = total_votes;
    }

    public Double getPercentage_of_votes() {
        return percentage_of_votes;
    }

    public void setPercentage_of_votes(Double percentage_of_votes) {
        this.percentage_of_votes = percentage_of_votes;
    }
}
