package br.com.testedit.teste_tecnico_dti.util;

import jakarta.validation.ConstraintViolation;
import java.util.Set;

public class ValidationResult {
    private final boolean valid;
    private final String errorMessage;

    public ValidationResult(Set<? extends ConstraintViolation<?>> violations) {
        this.valid = violations.isEmpty();
        this.errorMessage = violations.isEmpty() ? null : violations.iterator().next().getMessage();
    }

    public ValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

