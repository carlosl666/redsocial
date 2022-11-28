package com.caamal.ms.publicaciones.repository;

import com.caamal.ms.publicaciones.entity.Publicacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PublicacionRepository extends CrudRepository<Publicacion, String> {
    Optional<Publicacion> findByIdUsuario(Long idUsuario);
    void deleteByIdUsuario(Long idUsuario);
    List<Publicacion> findByEstatus(boolean estatus);
}
