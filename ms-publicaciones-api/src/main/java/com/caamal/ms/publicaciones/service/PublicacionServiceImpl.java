package com.caamal.ms.publicaciones.service;

import com.caamal.ms.publicaciones.entity.Publicacion;
import com.caamal.ms.publicaciones.repository.PublicacionRepository;
import com.caamal.ms.publicaciones.service.remote.UsuarioClientRest;
import com.caamal.ms.publicaciones.vo.ActualizarVO;
import com.caamal.ms.publicaciones.vo.MeGustaVo;
import com.caamal.ms.publicaciones.vo.RequestVO;
import com.caamal.ms.publicaciones.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService{

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Publicacion> findAllPublicacion() {
        return publicacionRepository.findByEstatus(Boolean.TRUE);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Publicacion> findByIdPublicacion(String id) {
        return publicacionRepository.findById(id);
    }

    @Override
    @Transactional
    public Publicacion savePublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    @Override
    @Transactional
    public Publicacion reqPublicacion(RequestVO publicacion) {
        Publicacion newPulic = new Publicacion();
        newPulic.setPublicacion(publicacion.getPublicacion());
        newPulic.setEstatus(Boolean.TRUE);
        newPulic.setFechaPublicacion(new Date().toString());
        return publicacionRepository.save(newPulic);
    }

    @Override
    @Transactional
    public Publicacion updatePublicacion(ActualizarVO actualizarVO, Publicacion publicacion) {
        publicacion.setPublicacion(actualizarVO.getPublicacion());
        publicacion.setEstatus(actualizarVO.getEstatus());
        return publicacionRepository.save(publicacion);
    }

    @Override
    @Transactional
    public Publicacion updateMegusta(MeGustaVo meGustaVo, Publicacion publicacion){
        publicacion.setMeGusta(meGustaVo.getMeGusta());
        return publicacionRepository.save(publicacion);
    }

    @Override
    @Transactional
    public void deletePublicacion(String id) {
        publicacionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deletePublicacionByIdUsuario(Long idUsuario) {
        publicacionRepository.deleteByIdUsuario(idUsuario);
    }

    @Override
    @Transactional
    public Optional<Publicacion> crearPublicacion(RequestVO vo, Long idUsuario) {
            Optional<UsuarioVO> usuarioVO = usuarioClientRest.findByIdUsuario(idUsuario);
            if(!usuarioVO.isPresent()){
                return Optional.empty();
            }
            Publicacion publicacion = new Publicacion();
            publicacion.setPublicacion(vo.getPublicacion());
            publicacion.setEstatus(Boolean.TRUE);
            publicacion.setFechaPublicacion(new Date().toString());
            publicacion.setIdUsuario(usuarioVO.get().getIdUsuario());
            return Optional.of(publicacionRepository.save(publicacion));
    }
}
