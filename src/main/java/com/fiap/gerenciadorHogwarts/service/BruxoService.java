package com.fiap.gerenciadorHogwarts.service;

import com.fiap.gerenciadorHogwarts.dto.request.BruxoRequestDTO;
import com.fiap.gerenciadorHogwarts.dto.response.BruxoResponseDTO;
import com.fiap.gerenciadorHogwarts.model.Bruxo;
import com.fiap.gerenciadorHogwarts.model.Casa;
import com.fiap.gerenciadorHogwarts.model.StatusSangue;
import com.fiap.gerenciadorHogwarts.projection.BruxoResumoProjection;
import com.fiap.gerenciadorHogwarts.repository.BruxoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BruxoService {

    private final BruxoRepository bruxoRepository;
    private final CasaService casaService;

    @Transactional
    public BruxoResponseDTO save(BruxoRequestDTO dto) {
        Casa casa = casaService.findEntityById(dto.casaId());
        Bruxo bruxo = Bruxo.builder()
                .nome(dto.nome())
                .idade(dto.idade())
                .statusSangue(dto.statusSangue())
                .casa(casa)
                .build();
        return toResponse(bruxoRepository.save(bruxo));
    }

    // Endpoint 1: Continua devolvendo a Projection
    public Page<BruxoResumoProjection> findByHouse(Long casaId, Pageable pageable) {
        casaService.findEntityById(casaId);
        return bruxoRepository.findByCasaId(casaId, pageable);
    }

    public List<BruxoResponseDTO> findByBloodStatus(StatusSangue status) {
        return bruxoRepository.findByStatusSangue(status).stream().map(this::toResponse).toList();
    }

    public Page<BruxoResponseDTO> findByAge(int minAge, Pageable pageable) {
        return bruxoRepository.findByIdadeGreaterThanEqual(minAge, pageable).map(this::toResponse);
    }

    public Bruxo findEntityById(Long id) {
        return bruxoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bruxo(a) não encontrado(a)!"));
    }

    @Transactional
    public void delete(Long id) {
        Bruxo bruxo = findEntityById(id);
        bruxoRepository.delete(bruxo);
    }
    @Transactional
    public BruxoResponseDTO update(Long id, BruxoRequestDTO dto) {
        Bruxo bruxo = findEntityById(id);
        Casa casa = casaService.findEntityById(dto.casaId()); // Valida se a nova casa informada existe

        bruxo.setNome(dto.nome());
        bruxo.setIdade(dto.idade());
        bruxo.setStatusSangue(dto.statusSangue());
        bruxo.setCasa(casa);

        return toResponse(bruxoRepository.save(bruxo));
    }

    private BruxoResponseDTO toResponse(Bruxo bruxo) {
        return new BruxoResponseDTO(
                bruxo.getId(),
                bruxo.getNome(),
                bruxo.getIdade(),
                bruxo.getStatusSangue(),
                bruxo.getCasa() != null ? bruxo.getCasa().getNome() : null
        );
    }
}