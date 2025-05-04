package api.sistemaVotaciones.voters.model;

import api.sistemaVotaciones.generics.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "voters")
public class Voter extends BaseEntity<Long> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "id_card", unique = true, nullable = false)
    private Long idCard;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "has_voted", nullable = false, columnDefinition = "BOOL DEFAULT 0")
    private Boolean hasVoted = false;

    // ---- Getters and Setters ----


    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(Boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}
