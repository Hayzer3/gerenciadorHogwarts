package com.fiap.gerenciadorHogwarts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_CASA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank
    private String fundador;

    private String cores;

    @OneToMany(mappedBy = "casa")
    private List<Bruxo> bruxos;
}
