package com.jei.applicacion.mapper.impl;

import com.jei.applicacion.mapper.IssueMapper;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Issue;
import com.jei.dominio.entidad.Prioridad;
import com.jei.dominio.entidad.Tipo;
import com.jei.web.dto.IssueRequestDto;
import com.jei.web.dto.IssueResponseDto;
import org.springframework.stereotype.Component;

@Component
public class IssueMapperImpl implements IssueMapper {
    @Override
    public Issue toDomain(IssueRequestDto issueRequestDto) {
        return Issue.builder()
                .nombre(issueRequestDto.getNombre())
                .usuario(issueRequestDto.getUsuario())
                .prioridad(Prioridad.valueOf(issueRequestDto.getPrioridad()))
                .estado(Estado.valueOf(issueRequestDto.getEstado()))
                .tipo(Tipo.valueOf(issueRequestDto.getTipo()))
                .epicos(issueRequestDto.getEpicos())
                .sprint(issueRequestDto.getSprint())
                .proyecto(issueRequestDto.getProyecto())
                .fecha(issueRequestDto.getFecha())
                .build();
    }

    @Override
    public IssueResponseDto toDto(Issue issue) {
        return IssueResponseDto.builder()
                .id(issue.getId())
                .nombre(issue.getNombre())
                .usuario(issue.getUsuario())
                .prioridad(issue.getPrioridad().name())
                .estado(issue.getEstado().name())
                .tipo(issue.getTipo().name())
                .epicos(issue.getEpicos())
                .sprint(issue.getSprint())
                .proyecto(issue.getProyecto())
                .fecha(issue.getFecha())
                .build();
    }
}
