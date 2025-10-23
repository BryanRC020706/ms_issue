package com.jei.applicacion.mapper;

import com.jei.dominio.entidad.Issue;
import com.jei.web.dto.IssueRequestDto;
import com.jei.web.dto.IssueResponseDto;

public interface IssueMapper {
    Issue toDomain(IssueRequestDto issueRequestDto);
    IssueResponseDto toDto(Issue issue);
}
