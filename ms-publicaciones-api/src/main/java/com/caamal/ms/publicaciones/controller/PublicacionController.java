package com.caamal.ms.publicaciones.controller;

import com.caamal.ms.publicaciones.entity.Publicacion;
import com.caamal.ms.publicaciones.service.PublicacionService;
import com.caamal.ms.publicaciones.vo.ActualizarVO;
import com.caamal.ms.publicaciones.vo.MeGustaVo;
import com.caamal.ms.publicaciones.vo.RequestVO;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("redsocial/publicacion/v1")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public ResponseEntity<List<Publicacion>> getAll(){
        return ResponseEntity.ok(publicacionService.findAllPublicacion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPublicacion(@PathVariable String id){
        return publicacionService.findByIdPublicacion(id)
                .map(publicacion -> ResponseEntity.ok(publicacion))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> savePublicacion(@RequestBody RequestVO publicacion){
            return ResponseEntity.status(HttpStatus.CREATED).body(publicacionService.reqPublicacion(publicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublicacion(@RequestBody ActualizarVO actualizarVO, @PathVariable String id){
        Optional<Publicacion> publication = publicacionService.findByIdPublicacion(id);
        if(publication.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(publicacionService.updatePublicacion(actualizarVO, publication.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/megusta/{id}")
    public ResponseEntity<?> updateMegusta(@RequestBody MeGustaVo meGustaVo, @PathVariable String id){
        return publicacionService.findByIdPublicacion(id)
                .map(publicacion -> ResponseEntity.status(HttpStatus.CREATED).body(publicacionService.updateMegusta(meGustaVo, publicacion)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublicacion(@PathVariable String id){
        return publicacionService.findByIdPublicacion(id)
                .map(publication -> {
                    publicacionService.deletePublicacion(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("eliminar-usuario/{idUsuario}")
    public ResponseEntity<?> deletePublicacionByIdUsuario(@PathVariable Long idUsuario){
        publicacionService.deletePublicacionByIdUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/publicar/{idUsuario}")
    public  ResponseEntity<?> crearpublicacion(@RequestBody RequestVO vo, @PathVariable Long idUsuario){
        Optional<Publicacion> publicacion;
        try {
            publicacion = publicacionService.crearPublicacion(vo, idUsuario);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap( "mensaje", "No se pudo publicar :(, o error en la comunicacion del MS, CODIGO: " + e.status()
                            + " " + e.getMessage()));
        }
        if(publicacion.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(publicacion.get());
        }
        return ResponseEntity.notFound().build();
    }
}
