package api.sistemaVotaciones.candidates.DTOs;

import jakarta.validation.constraints.NotBlank;

public class UpdateCandidateDtoRequest {

    @NotBlank(message = "The name cannot be blank")
    private String name;

    @NotBlank(message = "The party cannot be blank")
    private String party;

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
