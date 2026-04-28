package com.fiap.gerenciadorHogwarts.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VarinhaValidaValidator.class)
public @interface VarinhaValida {
    String message() default "Núcleo de varinha inválido. Use: Pena de Fênix, Corda de Coração de Dragão ou Pelo de Unicórnio.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}