package com.fiap.gerenciadorHogwarts.model;

import com.fiap.gerenciadorHogwarts.validation.VarinhaValida;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "TB_VARINHA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Varinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @VarinhaValida // Sua validação customizada
    private String materialNucleo;

    private Double comprimento;

    private String madeira;

    @OneToOne
    @JoinColumn(name = "id_bruxo", unique = true)
    private Bruxo bruxo;
}