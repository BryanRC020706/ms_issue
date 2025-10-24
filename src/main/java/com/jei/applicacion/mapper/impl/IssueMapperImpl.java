package com.jei.applicacion.mapper.impl;

import com.jei.applicacion.mapper.IssueMapper;
import com.jei.dominio.entidad.*;
import com.jei.web.dto.IssueRequestDto;
import com.jei.web.dto.IssueResponseDto;
import org.springframework.stereotype.Component;

@Component
public class IssueMapperImpl implements IssueMapper {
    @Override
    public Issue toDomain(IssueRequestDto issueRequestDto) {
        return Issue.builder()
                .nombre(issueRequestDto.getNombre())
                .usuario(Long.valueOf(issueRequestDto.getUsuario()))
                .prioridad(Prioridad.valueOf(issueRequestDto.getPrioridad()))
                .estado(Estado.valueOf(issueRequestDto.getEstado()))
                .tipo(Tipo.valueOf(issueRequestDto.getTipo()))
                .departamento(Departamento.valueOf(issueRequestDto.getDepartamento()))
                .epicos(Long.valueOf(issueRequestDto.getEpicos()))
                .sprint(issueRequestDto.getSprint())
                .proyecto(Long.valueOf(issueRequestDto.getProyecto()))
                .fecha(issueRequestDto.getFecha())
                .build();
    }

    @Override
    public IssueResponseDto toDto(Issue issue) {
        return IssueResponseDto.builder()
                .id(issue.getId())
                .nombre(issue.getNombre())
                .usuario(String.valueOf(issue.getUsuario()))
                .prioridad(issue.getPrioridad().name())
                .estado(issue.getEstado().name())
                .tipo(issue.getTipo().name())
                .departamento(issue.getDepartamento().name())
                .sprint(issue.getSprint())
                .fecha(issue.getFecha())
                .build();
    }
}
