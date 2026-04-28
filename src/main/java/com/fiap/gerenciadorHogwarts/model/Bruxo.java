package com.fiap.gerenciadorHogwarts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "TB_BRUXO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bruxo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Min(11)
    private int idade;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusSangue statusSangue;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    private Casa casa;

    @OneToOne(mappedBy = "bruxo", cascade = CascadeType.ALL)
    private Varinha varinha;
}