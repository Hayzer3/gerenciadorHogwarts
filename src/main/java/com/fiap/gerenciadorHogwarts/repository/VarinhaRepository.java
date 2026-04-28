package com.fiap.gerenciadorHogwarts.repository;


import com.fiap.gerenciadorHogwarts.model.Varinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarinhaRepository extends JpaRepository<Varinha, Long> {

    // Endpoint 3: Filtrar varinhas por material do núcleo
    List<Varinha> findByMaterialNucleoContainingIgnoreCase(String materialNucleo);

}