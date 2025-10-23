package com.jei.applicacion.service;

import com.jei.web.dto.IssueResponseDto;

import java.util.List;

public interface IssueService {
    List<IssueResponseDto> buscar();
    IssueResponseDto buscarPorId(Long id);
}
