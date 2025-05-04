package api.sistemaVotaciones.candidates.validation.annotation;

import api.sistemaVotaciones.candidates.validation.validator.ValidCandidateNotExistInVotersValidator;
import api.sistemaVotaciones.candidates.validation.validator.ValidCandidateNotExistValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidCandidateNotExistValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidCandidateNotExist {

    String message() default "The candidate already exists";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
