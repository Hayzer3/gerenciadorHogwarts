package com.fiap.gerenciadorHogwarts.service;

import com.fiap.gerenciadorHogwarts.dto.request.VarinhaRequestDTO;
import com.fiap.gerenciadorHogwarts.dto.response.VarinhaResponseDTO;
import com.fiap.gerenciadorHogwarts.model.Bruxo;
import com.fiap.gerenciadorHogwarts.model.Varinha;
import com.fiap.gerenciadorHogwarts.repository.VarinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VarinhaService {

    private final VarinhaRepository varinhaRepository;
    private final BruxoService bruxoService;

    @Transactional
    public VarinhaResponseDTO save(VarinhaRequestDTO dto) {
        Bruxo bruxo = bruxoService.findEntityById(dto.bruxoId());
        Varinha varinha = Varinha.builder()
                .materialNucleo(dto.materialNucleo())
                .comprimento(dto.comprimento())
                .madeira(dto.madeira())
                .bruxo(bruxo)
                .build();
        return toResponse(varinhaRepository.save(varinha));
    }

    public List<VarinhaResponseDTO> filterByCoreMaterial(String material) {
        List<Varinha> varinhas = varinhaRepository.findByMaterialNucleoContainingIgnoreCase(material);
        if (varinhas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma varinha encontrada.");
        }
        return varinhas.stream().map(this::toResponse).toList();
    }

    public Varinha findEntityById(Long id) {
        return varinhaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Varinha não encontrada!"));
    }

    @Transactional
    public void delete(Long id) {
        Varinha varinha = findEntityById(id);
        varinhaRepository.delete(varinha);
    }

    // Listar todas as varinhas
    public List<VarinhaResponseDTO> findAll() {
        return varinhaRepository.findAll().stream().map(this::toResponse).toList();
    }

    // Buscar uma varinha específica por ID
    public VarinhaResponseDTO findById(Long id) {
        Varinha varinha = findEntityById(id); // Usa o método que já criamos
        return toResponse(varinha);
    }

    @Transactional
    public VarinhaResponseDTO update(Long id, VarinhaRequestDTO dto) {
        Varinha varinha = findEntityById(id);
        Bruxo bruxo = bruxoService.findEntityById(dto.bruxoId()); // Valida se o novo dono existe

        varinha.setMaterialNucleo(dto.materialNucleo());
        varinha.setComprimento(dto.comprimento());
        varinha.setMadeira(dto.madeira());
        varinha.setBruxo(bruxo);

        return toResponse(varinhaRepository.save(varinha));
    }

    private VarinhaResponseDTO toResponse(Varinha varinha) {
        return new VarinhaResponseDTO(
                varinha.getId(),
                varinha.getMaterialNucleo(),
                varinha.getComprimento(),
                varinha.getMadeira(),
                varinha.getBruxo() != null ? varinha.getBruxo().getNome() : null
        );
    }
}