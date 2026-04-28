package com.fiap.gerenciadorHogwarts.controller;

import com.fiap.gerenciadorHogwarts.dto.request.CasaRequestDTO;
import com.fiap.gerenciadorHogwarts.dto.response.CasaResponseDTO;
import com.fiap.gerenciadorHogwarts.service.CasaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casas")
@RequiredArgsConstructor
public class CasaController {

    private final CasaService casaService;

    @PostMapping
    public ResponseEntity<CasaResponseDTO> create(@RequestBody @Valid CasaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(casaService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<CasaResponseDTO>> findAll() {
        return ResponseEntity.ok(casaService.findAll());
    }

    @GetMapping("/fundador")
    public ResponseEntity<CasaResponseDTO> findByFounder(@RequestParam String nome) {
        return ResponseEntity.ok(casaService.findByFounder(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        casaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasaResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid CasaRequestDTO dto) {
        return ResponseEntity.ok(casaService.update(id, dto));
    }
}