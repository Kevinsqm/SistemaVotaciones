package api.sistemaVotaciones.votes.model;

import api.sistemaVotaciones.candidates.model.Candidate;
import api.sistemaVotaciones.generics.model.BaseEntity;
import api.sistemaVotaciones.voters.model.Voter;
import jakarta.persistence.*;

@Entity
@Table(name = "votes")
public class Vote extends BaseEntity<Long> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voter_id", nullable = false, unique = true)
    private Voter voter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    // ---- Getters and Setters ----

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
