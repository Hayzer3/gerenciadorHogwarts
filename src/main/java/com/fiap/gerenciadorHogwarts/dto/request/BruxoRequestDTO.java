package com.fiap.gerenciadorHogwarts.dto.request;

import com.fiap.gerenciadorHogwarts.model.StatusSangue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BruxoRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @Min(value = 11, message = "Idade mínima é 11 anos") int idade,
        @NotNull(message = "Status de sangue é obrigatório") StatusSangue statusSangue,
        @NotNull(message = "O ID da casa é obrigatório") Long casaId
) {}