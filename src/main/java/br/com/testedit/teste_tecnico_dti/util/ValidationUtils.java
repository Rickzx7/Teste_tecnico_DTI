package br.com.testedit.teste_tecnico_dti.util;

import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class ValidationUtils {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> void validate(T obj) {
        log.debug("Starting Bean Validation for object: {}", obj.getClass().getSimpleName());
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            log.warn("Bean Validation failed with {} violations", violations.size());
            StringBuilder errors = new StringBuilder("Validation errors occurred:\n");
            for (ConstraintViolation<T> violation : violations) {
                errors.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("\n");
                log.error("Validation error: {}",
                        violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            log.error("Bean Validation failed: {}", errors.toString());
            throw new IllegalArgumentException(errors.toString());
        }
        log.info("Bean Validation passed successfully for object: {}", obj.getClass().getSimpleName());
    }

    public static <T> ValidationResult validateField(T obj, String fieldName) {
        Set<ConstraintViolation<T>> violations = validator.validateProperty(obj, fieldName);
        return new ValidationResult(violations);
    }

    public static <T> ValidationResult validate(T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj, groups);
        return new ValidationResult(violations);
    }
}