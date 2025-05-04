package api.sistemaVotaciones.candidates.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CandidateDtoRequest {

    @Min(value = 10000, message = "The ID Card must be at least 5 digits")
    private Long id_card;

    @NotBlank(message = "The name cannot be blank")
    private String name;

    @NotBlank(message = "The party cannot be blank")
    private String party;

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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
