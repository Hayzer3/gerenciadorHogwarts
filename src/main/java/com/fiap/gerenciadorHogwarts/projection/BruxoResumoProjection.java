package com.fiap.gerenciadorHogwarts.projection;

// O Spring Data vai ler essa interface e criar um SELECT otimizado
// buscando apenas o Id, o Nome e o Nome da Casa do bruxo.
public interface BruxoResumoProjection {

    Long getId();
    String getNome();

    // Para pegar um dado que está dentro de um relacionamento (Casa)
    CasaResumo getCasa();

    interface CasaResumo {
        String getNome();
    }
}