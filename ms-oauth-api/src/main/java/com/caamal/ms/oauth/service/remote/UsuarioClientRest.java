package com.caamal.ms.oauth.service.remote;


import com.caamal.ms.oauth.vo.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-usuarios-api", url = "${ms.usuarios.api.url}")
public interface UsuarioClientRest {
    @GetMapping("/username/{name}")
    Optional<ApiResponse> findByUsername(@PathVariable String name);
}
