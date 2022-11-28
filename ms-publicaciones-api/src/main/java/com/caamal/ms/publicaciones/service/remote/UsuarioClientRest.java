package com.caamal.ms.publicaciones.service.remote;

import com.caamal.ms.publicaciones.vo.UsuarioVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-usuarios-api", url = "${ms.usuarios.api.url}")
public interface UsuarioClientRest {

    @GetMapping("/{idUsuario}")
    Optional<UsuarioVO> findByIdUsuario(@PathVariable Long idUsuario);
}
