package com.fiap.gerenciadorHogwarts.controller;

import com.fiap.gerenciadorHogwarts.dto.request.BruxoRequestDTO;
import com.fiap.gerenciadorHogwarts.dto.response.BruxoResponseDTO;
import com.fiap.gerenciadorHogwarts.model.StatusSangue;
import com.fiap.gerenciadorHogwarts.projection.BruxoResumoProjection;
import com.fiap.gerenciadorHogwarts.service.BruxoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bruxos")
@RequiredArgsConstructor
public class BruxoController {

    private final BruxoService bruxoService;

    @PostMapping
    public ResponseEntity<BruxoResponseDTO> create(@RequestBody @Valid BruxoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bruxoService.save(dto));
    }

    @GetMapping("/casa/{casaId}")
    public ResponseEntity<Page<BruxoResumoProjection>> findByHouse(
            @PathVariable Long casaId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(bruxoService.findByHouse(casaId, pageable));
    }

    @GetMapping("/sangue/{status}")
    public ResponseEntity<List<BruxoResponseDTO>> findByBloodStatus(@PathVariable StatusSangue status) {
        return ResponseEntity.ok(bruxoService.findByBloodStatus(status));
    }

    @GetMapping("/idade")
    public ResponseEntity<Page<BruxoResponseDTO>> findByAge(
            @RequestParam(defaultValue = "11") int idadeMinima,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(bruxoService.findByAge(idadeMinima, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bruxoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BruxoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid BruxoRequestDTO dto) {
        return ResponseEntity.ok(bruxoService.update(id, dto));
    }
}