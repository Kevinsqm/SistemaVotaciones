package api.sistemaVotaciones.candidates.validation.annotation;

import api.sistemaVotaciones.candidates.validation.validator.ValidCandidateNotExistInVotersValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidCandidateNotExistInVotersValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidCandidateNotExistInVoters {

    String message() default "The candidate is already a registered as a voter";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};

}
