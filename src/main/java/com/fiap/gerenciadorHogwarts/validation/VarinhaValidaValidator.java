package com.fiap.gerenciadorHogwarts.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class VarinhaValidaValidator implements ConstraintValidator<VarinhaValida, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        List<String> nucleosValidos = Arrays.asList("Pena de Fênix", "Pelo de Unicórnio", "Corda de Coração de Dragão");
        return nucleosValidos.contains(value);
    }
}
