package api.sistemaVotaciones.candidates.validation.validator;

import api.sistemaVotaciones.candidates.service.interfaces.ICandidateService;
import api.sistemaVotaciones.candidates.validation.annotation.ValidCandidateNotExist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCandidateNotExistValidator implements ConstraintValidator<ValidCandidateNotExist, Long> {

    private final ICandidateService candidateService;

    public ValidCandidateNotExistValidator(ICandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public void initialize(ValidCandidateNotExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return !candidateService.existsByIdCard(value);
    }
}
