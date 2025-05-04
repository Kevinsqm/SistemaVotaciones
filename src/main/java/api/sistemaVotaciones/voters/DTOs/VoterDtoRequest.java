package api.sistemaVotaciones.voters.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class VoterDtoRequest {

    @Min(value = 10000, message = "The id_card must be at least 5 digits")
    private Long id_card;

    @NotBlank(message = "The name cannot be blank")
    private String name;

    @Email(message = "The email must be valid")
    private String email;

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

}
