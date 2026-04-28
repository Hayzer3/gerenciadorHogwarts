package com.fiap.gerenciadorHogwarts.dto.response;

public record CasaResponseDTO(
        Long id,
        String nome,
        String fundador,
        String cores
) {}