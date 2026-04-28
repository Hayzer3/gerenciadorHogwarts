package com.fiap.gerenciadorHogwarts.repository;



import com.fiap.gerenciadorHogwarts.model.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {

    // Endpoint 4: Buscar casa pelo nome do fundador (ignorando maiúsculas/minúsculas)
    Optional<Casa> findByFundadorContainingIgnoreCase(String fundador);

}