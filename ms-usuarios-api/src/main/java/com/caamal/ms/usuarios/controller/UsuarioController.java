package com.caamal.ms.usuarios.controller;

import com.caamal.ms.usuarios.entity.Usuario;
import com.caamal.ms.usuarios.service.UsuarioException;
import com.caamal.ms.usuarios.service.UsuarioService;
import com.caamal.ms.usuarios.utils.ApiUtils;
import com.caamal.ms.usuarios.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("redsocial/usuario/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ApiUtils apiUtils;

    @GetMapping
    public List<Usuario> listAllUsuario(){
        return usuarioService.listAllUsuario();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> findByIdUsuario(@PathVariable Long idUsuario){
        return usuarioService.findByIdUsuario(idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{name}")
    public ApiResponse findByUsername(@PathVariable String name){
        return usuarioService.findByUsername(name)
                .map(user -> new ApiResponse("Resultado de la busqueda: " + name, HttpStatus.OK.toString(), user))//.map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseThrow(()-> UsuarioException.from("No se encontro el usuario: " + name, name));//.orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return apiUtils.validarRequest(bindingResult);
        }
        if(apiUtils.validarEmail(usuario.getEmail(), 1, usuario)){
            return ResponseEntity.badRequest().body(
                    Collections.singletonMap("mensaje","Ya existe un usuario con ese correo electronico!"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> updateUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult, @PathVariable Long idUsuario){
        if(bindingResult.hasErrors()){
            return apiUtils.validarRequest(bindingResult);
        }
        Optional<Usuario> user = usuarioService.findByIdUsuario(idUsuario);
        if(user.isPresent()){
            Usuario updateUser = user.get();
            if(apiUtils.validarEmail(usuario.getEmail(), 0, updateUser)){
                return ResponseEntity.badRequest().body(
                        Collections.singletonMap("mensaje","Ya existe un usuario con ese correo electronico!"));
            }
            updateUser.setNombre(usuario.getNombre());
            updateUser.setEmail(usuario.getEmail());
            updateUser.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(updateUser));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long idUsuario){
        return usuarioService.findByIdUsuario(idUsuario)
                .map(usuario -> {
                    usuarioService.deleteUsuario(usuario.getIdUsuario());
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
