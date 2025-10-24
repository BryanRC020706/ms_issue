package com.jei.applicacion.client;

import com.jei.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-epicas",
        url = "http://localhost:8083/api/epicas",
        configuration = FeignSecurityConfig.class
)
public interface EpicaClient {

    @GetMapping("/{id}")
    EpicaResponseDto buscarPorId(@PathVariable("id") Long id);
}