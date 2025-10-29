package com.jei.web.controller;

import com.jei.applicacion.service.IssueService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.web.dto.IssueRequestDto;
import com.jei.web.dto.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<IssueResponseDto>> buscar(@RequestParam(value = "departamento", required = false) Departamento departamento,
                                                         @RequestParam(value = "estado", required = false) Estado estado) {
        List<IssueResponseDto> issues;

        if (departamento != null && estado != null) {
            issues = issueService.buscarPorDepartamentoYEstado(departamento, estado);
        }
        else {
            issues = issueService.buscarPorDepartamento(departamento);
        }

        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDto> buscarPorId(@PathVariable Long id) {
        IssueResponseDto issue = issueService.buscarPorId(id);
        return ResponseEntity.ok(issue);
    }
    @PostMapping
    public ResponseEntity<IssueResponseDto> crear(@RequestBody IssueRequestDto issueRequest) {
        IssueResponseDto creado = issueService.crear(issueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Editar un issue existente
    @PutMapping("/{id}")
    public ResponseEntity<IssueResponseDto> editar(@PathVariable Long id,
                                                   @RequestBody IssueRequestDto issueRequest) {
        IssueResponseDto actualizado = issueService.editar(id, issueRequest);
        return ResponseEntity.ok(actualizado);
    }
}
