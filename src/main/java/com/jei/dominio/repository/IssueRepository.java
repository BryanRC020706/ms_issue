package com.jei.dominio.repository;

import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Issue;
import com.jei.web.dto.IssueResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByDepartamentoAndEstado(Departamento departamento, Estado estado);
    List<Issue> findByDepartamento(Departamento departamento);
}
