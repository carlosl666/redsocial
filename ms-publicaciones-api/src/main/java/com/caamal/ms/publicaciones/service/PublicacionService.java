package com.caamal.ms.publicaciones.service;

import com.caamal.ms.publicaciones.entity.Publicacion;
import com.caamal.ms.publicaciones.vo.ActualizarVO;
import com.caamal.ms.publicaciones.vo.MeGustaVo;
import com.caamal.ms.publicaciones.vo.RequestVO;

import java.util.List;
import java.util.Optional;

public interface PublicacionService {
    List<Publicacion> findAllPublicacion();
    Optional<Publicacion> findByIdPublicacion(String id);
    Publicacion savePublicacion(Publicacion publicacion);
    Publicacion reqPublicacion(RequestVO publicacion);
    Publicacion updatePublicacion(ActualizarVO actualizarVO, Publicacion publicacion);
    void deletePublicacion(String id);
    void deletePublicacionByIdUsuario(Long idUsuario);
    Publicacion updateMegusta(MeGustaVo meGustaVo, Publicacion publicacion);

    Optional<Publicacion> crearPublicacion(RequestVO vo, Long idUsuario);
}
