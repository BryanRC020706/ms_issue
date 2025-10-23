package com.jei.dominio.repository;

import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByDepartamento(Departamento departamento);
}
