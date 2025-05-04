package api.sistemaVotaciones.voters.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateVoterDtoRequest {

    @NotBlank(message = "The name cannot be blank")
    private String name;

    @Email(message = "The email must be valid")
    private String email;

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
