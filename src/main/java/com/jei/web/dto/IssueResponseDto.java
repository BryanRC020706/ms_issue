package com.jei.web.dto;

import com.jei.applicacion.client.EpicaResponseDto;
import com.jei.applicacion.client.ProyectoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueResponseDto {
    private Long id;
    private String nombre;
    private String usuario;
    private String prioridad;
    private String estado;
    private String departamento;
    private String tipo;
    private EpicaResponseDto epica;
    private String sprint;
    private ProyectoResponseDto proyecto;
    private LocalDate fecha;
}
