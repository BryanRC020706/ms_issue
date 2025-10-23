package com.jei.web.controller;

import com.jei.applicacion.service.IssueService;
import com.jei.web.dto.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<IssueResponseDto>> buscar() {
        List<IssueResponseDto> issues = issueService.buscar();
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDto> buscarPorId(@PathVariable Long id) {
        IssueResponseDto issue = issueService.buscarPorId(id);
        return ResponseEntity.ok(issue);
    }
}
