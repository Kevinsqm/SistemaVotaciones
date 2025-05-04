package api.sistemaVotaciones.voters.DTOs;

import jakarta.persistence.Column;

public class VoterDtoResponse {

    private Long id;

    private Long id_card;

    private String name;

    private String email;

    private Boolean has_voted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_card() {
        return id_card;
    }

    public void setId_card(Long id_card) {
        this.id_card = id_card;
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

    public Boolean getHas_voted() {
        return has_voted;
    }

    public void setHas_voted(Boolean has_voted) {
        this.has_voted = has_voted;
    }
}
