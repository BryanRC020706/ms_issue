package com.jei.applicacion.service.Impl;

import com.jei.applicacion.mapper.IssueMapper;
import com.jei.applicacion.service.IssueService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Issue;
import com.jei.dominio.repository.IssueRepository;
import com.jei.web.dto.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;

    @Override
    public List<IssueResponseDto> buscar() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .map(issueMapper::toDto)
                .toList();
    }

    @Override
    public IssueResponseDto buscarPorId(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue no encontrado con ID: " + id));
        return issueMapper.toDto(issue);
    }

    @Override
    public List<IssueResponseDto> buscarPorDepartamento(Departamento departamento) {
        return issueRepository.findByDepartamento(departamento)
                .stream()
                .map(issueMapper::toDto)
                .toList();
    }
}
