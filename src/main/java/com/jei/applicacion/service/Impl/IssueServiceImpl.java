package com.jei.applicacion.service.Impl;

import com.jei.applicacion.client.*;
import com.jei.applicacion.mapper.IssueMapper;
import com.jei.applicacion.service.IssueService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Issue;
import com.jei.dominio.repository.IssueRepository;
import com.jei.web.dto.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;
    private final ProyectoClient proyectoClient;
    private final EpicaClient epicaClient;
    private final UsuarioClient usuarioClient;


    @Override
    public List<IssueResponseDto> buscar() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .map(issue -> {
                    IssueResponseDto dto = issueMapper.toDto(issue);

                    if (issue.getEpicos() != null) {
                        try {
                            EpicaResponseDto epica = epicaClient.buscarPorId(issue.getEpicos());
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(issue.getProyecto());
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(issue.getUsuario());
                            dto.setEpica(epica);
                            dto.setUsuario(usuario);
                            dto.setProyecto(proyecto);
                        } catch (Exception e) {
                            System.out.println("no se encontro la epica con ID: " + issue.getEpicos());
                        }
                    }

                    return dto;
                })
                .toList();
    }

    @Override
    public IssueResponseDto buscarPorId(Long id) {
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se encontro issue con ID: " + id));

        IssueResponseDto dto = issueMapper.toDto(issue);

        if (issue.getEpicos() != null) {
            try {
                EpicaResponseDto epica = epicaClient.buscarPorId(issue.getEpicos());
                ProyectoResponseDto proyecto = proyectoClient.buscarPorId(issue.getProyecto());
                UsuarioResponseDto usuario = usuarioClient.buscarPorId(issue.getUsuario());
                dto.setEpica(epica);
                dto.setUsuario(usuario);
                dto.setProyecto(proyecto);
            } catch (Exception e) {
                System.out.println("no se encontro la epica con ID: " + issue.getEpicos());
            }
        }

        return dto;
    }

    @Override
    public List<IssueResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        @SuppressWarnings("unchecked")
        Map<String, Object> details = (Map<String, Object>) auth.getDetails();
        String userDepartamento = (String) details.get("departamento");
        String userRole = (String) details.get("role");
        Departamento departamentoFinal = "ADMIN".equalsIgnoreCase(userRole)
                ? departamento
                : Departamento.valueOf(userDepartamento);
        return issueRepository.findByDepartamentoAndEstado(departamentoFinal, estado)
                .stream()
                .map(issue -> {
                    IssueResponseDto dto = issueMapper.toDto(issue);
                    if (issue.getEpicos() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(issue.getProyecto());
                            EpicaResponseDto epica = epicaClient.buscarPorId(issue.getEpicos());
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(issue.getUsuario());
                            dto.setEpica(epica);
                            dto.setProyecto(proyecto);
                            dto.setUsuario(usuario);
                        } catch (Exception ignored) {}
                    }
                    return dto;
                })
                .toList();
    }

    @Override
    public List<IssueResponseDto> buscarPorDepartamento(Departamento departamento) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userRole = "ADMIN";
        String userDepartamento = "COMERCIAL";

        if (auth != null && auth.getPrincipal() != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Jwt jwt) {
                userRole = jwt.getClaimAsString("role");
                userDepartamento = jwt.getClaimAsString("departamento");
            }
        }

        Departamento departamentoFinal = "ADMIN".equalsIgnoreCase(userRole)
                ? departamento
                : Departamento.valueOf(userDepartamento);
        return issueRepository.findByDepartamento(departamentoFinal)
                .stream()
                .map(issue -> {
                    IssueResponseDto dto = issueMapper.toDto(issue);
                    if (issue.getEpicos() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(issue.getProyecto());
                            EpicaResponseDto epica = epicaClient.buscarPorId(issue.getEpicos());
                            UsuarioResponseDto usuario = usuarioClient.buscarPorId(issue.getUsuario());
                            dto.setEpica(epica);
                            dto.setProyecto(proyecto);
                            dto.setUsuario(usuario);
                        } catch (Exception ignored) {}
                    }
                    return dto;
                })
                .toList();
    }

}
