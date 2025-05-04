package api.sistemaVotaciones.candidates.validation.validator;

import api.sistemaVotaciones.candidates.validation.annotation.ValidCandidateNotExistInVoters;
import api.sistemaVotaciones.voters.service.interfaces.IVoterService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCandidateNotExistInVotersValidator implements ConstraintValidator<ValidCandidateNotExistInVoters, Long> {

    private final IVoterService voterService;

    public ValidCandidateNotExistInVotersValidator(IVoterService voterService) {
        this.voterService = voterService;
    }

    @Override
    public void initialize(ValidCandidateNotExistInVoters constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return !voterService.existsByIdCard(value);
    }
}
