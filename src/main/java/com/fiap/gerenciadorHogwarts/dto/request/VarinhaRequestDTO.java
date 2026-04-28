package com.fiap.gerenciadorHogwarts.dto.request;

import com.fiap.gerenciadorHogwarts.validation.VarinhaValida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VarinhaRequestDTO(
        @NotBlank @VarinhaValida String materialNucleo,
        Double comprimento,
        String madeira,
        @NotNull(message = "O ID do bruxo dono da varinha é obrigatório") Long bruxoId
) {}