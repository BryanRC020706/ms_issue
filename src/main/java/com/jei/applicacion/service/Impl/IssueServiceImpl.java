package com.jei.applicacion.service.Impl;

import com.jei.applicacion.client.EpicaClient;
import com.jei.applicacion.client.EpicaResponseDto;
import com.jei.applicacion.client.ProyectoClient;
import com.jei.applicacion.client.ProyectoResponseDto;
import com.jei.applicacion.mapper.IssueMapper;
import com.jei.applicacion.service.IssueService;
import com.jei.dominio.entidad.Departamento;
import com.jei.dominio.entidad.Estado;
import com.jei.dominio.entidad.Issue;
import com.jei.dominio.repository.IssueRepository;
import com.jei.web.dto.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;
    private final ProyectoClient proyectoClient;
    private final EpicaClient epicaClient;

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
                            dto.setEpica(epica);
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
                dto.setEpica(epica);
                dto.setProyecto(proyecto);
            } catch (Exception e) {
                System.out.println("no se encontro la epica con ID: " + issue.getEpicos());
            }
        }

        return dto;
    }

    @Override
    public List<IssueResponseDto> buscarPorDepartamentoYEstado(Departamento departamento, Estado estado) {
        return issueRepository.findByDepartamentoAndEstado(departamento, estado)
                .stream()
                .map(issue -> {
                    IssueResponseDto dto = issueMapper.toDto(issue);
                    if (issue.getEpicos() != null) {
                        try {
                            ProyectoResponseDto proyecto = proyectoClient.buscarPorId(issue.getProyecto());
                            EpicaResponseDto epica = epicaClient.buscarPorId(issue.getEpicos());
                            dto.setEpica(epica);
                            dto.setProyecto(proyecto);
                        } catch (Exception ignored) {}
                    }
                    return dto;
                })
                .toList();
    }

}
