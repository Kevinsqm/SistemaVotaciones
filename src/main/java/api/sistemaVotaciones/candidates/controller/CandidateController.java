package api.sistemaVotaciones.candidates.controller;

import api.sistemaVotaciones.candidates.DTOs.CandidateDtoRequest;
import api.sistemaVotaciones.candidates.DTOs.CandidateDtoResponse;
import api.sistemaVotaciones.candidates.DTOs.UpdateCandidateDtoRequest;
import api.sistemaVotaciones.candidates.service.interfaces.ICandidateService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    private final ICandidateService candidateService;

    public CandidateController(ICandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Page<CandidateDtoResponse>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok(candidateService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDtoResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(candidateService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CandidateDtoRequest candidateDto){
        var candidate = candidateService.save(candidateDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(candidate.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCandidateDtoRequest dtoRequest, @PathVariable Long id){
        candidateService.update(dtoRequest, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        candidateService.delete(id);
        return ResponseEntity.ok().build();
    }


}

