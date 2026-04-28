package com.fiap.gerenciadorHogwarts.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CasaRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O fundador é obrigatório") String fundador,
        String cores
) {}