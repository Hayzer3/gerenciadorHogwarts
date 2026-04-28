package com.fiap.gerenciadorHogwarts.dto.response;

import com.fiap.gerenciadorHogwarts.model.StatusSangue;

public record BruxoResponseDTO(
        Long id,
        String nome,
        int idade,
        StatusSangue statusSangue,
        String nomeCasa // Devolvemos apenas o nome da casa para ficar limpo
) {}