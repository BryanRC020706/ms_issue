package com.jei.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueRequestDto {
    private String nombre;
    private String usuario;
    private String prioridad;
    private String estado;
    private String tipo;
    private String departamento;
    private String epicos;
    private String sprint;
    private String proyecto;
    private LocalDate fecha;
}
