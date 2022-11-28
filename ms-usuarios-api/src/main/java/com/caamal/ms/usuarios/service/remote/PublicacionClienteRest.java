package com.caamal.ms.usuarios.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-publicaciones-api", url = "${ms.publicaciones.api.url}")
public interface PublicacionClienteRest {

    @DeleteMapping("eliminar-usuario/{idUsuario}")
    void deletePublicacionByIdUsuario(@PathVariable Long idUsuario);
}