package com.fiap.gerenciadorHogwarts.repository;



import com.fiap.gerenciadorHogwarts.model.Bruxo;
import com.fiap.gerenciadorHogwarts.model.StatusSangue;
import com.fiap.gerenciadorHogwarts.projection.BruxoResumoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BruxoRepository extends JpaRepository<Bruxo, Long> {

    // Endpoint 1: Buscar bruxo por casa + PROJECTION + PAGINAÇÃO
    // O Spring sabe mapear o "casa_id" pelo nome do método "findByCasaId"
    Page<BruxoResumoProjection> findByCasaId(Long casaId, Pageable pageable);

    // Endpoint 2: Buscar bruxos por status de sangue
    List<Bruxo> findByStatusSangue(StatusSangue statusSangue);

    // Endpoint 5: Listar bruxos por idade com paginação e ordenação
    // O Pageable que vier do Controller já vai conter a instrução de ordenação (ex: Sort.by("idade").ascending())
    Page<Bruxo> findByIdadeGreaterThanEqual(int idadeMinima, Pageable pageable);
}
