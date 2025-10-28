package com.jei.applicacion.service;

import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.web.dto.IssueResponseDto;

import java.util.List;

public interface IssueService {
    List<IssueResponseDto> buscar();
    IssueResponseDto buscarPorId(Long id);
    List<IssueResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado);
    List<IssueResponseDto> buscarPorDepartamento(Departamento departamento);
}
