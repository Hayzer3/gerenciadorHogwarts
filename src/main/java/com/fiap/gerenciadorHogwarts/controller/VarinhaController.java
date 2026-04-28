package com.fiap.gerenciadorHogwarts.controller;

import com.fiap.gerenciadorHogwarts.dto.request.VarinhaRequestDTO;
import com.fiap.gerenciadorHogwarts.dto.response.VarinhaResponseDTO;
import com.fiap.gerenciadorHogwarts.service.VarinhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/varinhas")
@RequiredArgsConstructor
public class VarinhaController {

    private final VarinhaService varinhaService;

    @PostMapping
    public ResponseEntity<VarinhaResponseDTO> create(@RequestBody @Valid VarinhaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(varinhaService.save(dto));
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<VarinhaResponseDTO>> filterByCore(@RequestParam String nucleo) {
        return ResponseEntity.ok(varinhaService.filterByCoreMaterial(nucleo));
    }

    // Endpoint para listar TODAS as varinhas (GET /varinhas)
    @GetMapping
    public ResponseEntity<List<VarinhaResponseDTO>> findAll() {
        return ResponseEntity.ok(varinhaService.findAll());
    }

    // Endpoint para buscar uma varinha pelo ID (GET /varinhas/1)
    @GetMapping("/{id}")
    public ResponseEntity<VarinhaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(varinhaService.findById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<VarinhaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid VarinhaRequestDTO dto) {
        return ResponseEntity.ok(varinhaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        varinhaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}