package api.sistemaVotaciones.voters.controller;

import api.sistemaVotaciones.voters.DTOs.UpdateVoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoRequest;
import api.sistemaVotaciones.voters.DTOs.VoterDtoResponse;
import api.sistemaVotaciones.voters.service.interfaces.IVoterService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/voters")
public class VoterController {

    private final IVoterService voterService;

    public VoterController(IVoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping
    public ResponseEntity<Page<VoterDtoResponse>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(voterService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterDtoResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(voterService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid VoterDtoRequest voterDto){
        var voter = voterService.save(voterDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(voter.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateVoterDtoRequest voterDto, @PathVariable Long id){
        voterService.update(voterDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        voterService.delete(id);
        return ResponseEntity.ok().build();
    }
}
