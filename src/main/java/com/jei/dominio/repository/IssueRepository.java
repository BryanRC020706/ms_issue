package com.jei.dominio.repository;

import com.jei.dominio.entidad.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
