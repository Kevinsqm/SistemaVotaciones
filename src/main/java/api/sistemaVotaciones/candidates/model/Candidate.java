package api.sistemaVotaciones.candidates.model;

import api.sistemaVotaciones.generics.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate extends BaseEntity<Long> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "id_card", unique = true, nullable = false)
    private Long idCard;

    @Column(nullable = false)
    private String name;

    private String party;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer votes = 0;

    // ---- Getters and Setters ----

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
