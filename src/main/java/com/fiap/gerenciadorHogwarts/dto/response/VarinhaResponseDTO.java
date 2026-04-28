package com.fiap.gerenciadorHogwarts.dto.response;

public record VarinhaResponseDTO(
        Long id,
        String materialNucleo,
        Double comprimento,
        String madeira,
        String nomeBruxoDono // Devolvemos apenas o nome do dono
) {}