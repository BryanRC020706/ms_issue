package com.jei.web.controller;

import com.jei.applicacion.service.IssueService;
import com.jei.dominio.entidad.Departamento;
import com.jei.web.dto.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<IssueResponseDto>> buscar(@RequestParam(value = "departamento", required = false) Departamento departamento) {
        List<IssueResponseDto> issues;
        if (departamento != null) {
            issues = issueService.buscarPorDepartamento(departamento);
        } else {
            issues = issueService.buscar();
        }
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDto> buscarPorId(@PathVariable Long id) {
        IssueResponseDto issue = issueService.buscarPorId(id);
        return ResponseEntity.ok(issue);
    }
}
