package com.fiap.gerenciadorHogwarts.service;

import com.fiap.gerenciadorHogwarts.dto.request.CasaRequestDTO;
import com.fiap.gerenciadorHogwarts.dto.response.CasaResponseDTO;
import com.fiap.gerenciadorHogwarts.model.Casa;
import com.fiap.gerenciadorHogwarts.repository.CasaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CasaService {

    private final CasaRepository casaRepository;

    @Transactional
    public CasaResponseDTO save(CasaRequestDTO dto) {
        Casa casa = Casa.builder()
                .nome(dto.nome())
                .fundador(dto.fundador())
                .cores(dto.cores())
                .build();
        return toResponse(casaRepository.save(casa));
    }

    public List<CasaResponseDTO> findAll() {
        return casaRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CasaResponseDTO findByFounder(String founder) {
        Casa casa = casaRepository.findByFundadorContainingIgnoreCase(founder)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa não encontrada."));
        return toResponse(casa);
    }

    // Método auxiliar que retorna a Entidade (usado internamente e por outros Services)
    public Casa findEntityById(Long id) {
        return casaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Casa não encontrada!"));
    }

    @Transactional
    public void delete(Long id) {
        Casa casa = findEntityById(id);
        casaRepository.delete(casa);
    }

    // Conversor Entidade -> ResponseDTO
    private CasaResponseDTO toResponse(Casa casa) {
        return new CasaResponseDTO(casa.getId(), casa.getNome(), casa.getFundador(), casa.getCores());
    }

    @Transactional
    public CasaResponseDTO update(Long id, CasaRequestDTO dto) {
        // Busca a casa existente ou devolve 404
        Casa casa = findEntityById(id);

        // Atualiza os dados
        casa.setNome(dto.nome());
        casa.setFundador(dto.fundador());
        casa.setCores(dto.cores());

        // Salva e converte para ResponseDTO
        return toResponse(casaRepository.save(casa));
    }


}