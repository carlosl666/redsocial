package com.caamal.ms.oauth.service.remote;

import com.caamal.ms.oauth.vo.ApiResponse;
import com.caamal.ms.oauth.vo.RoleVO;
import com.caamal.ms.oauth.vo.UsuarioVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

    @Autowired
    private UsuarioClientRest usuarioClientRest;

    @Autowired
    private ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApiResponse> usuarioVO = Optional.empty();
        try {
            usuarioVO = usuarioClientRest.findByUsername(username);
        } catch (FeignException e) {
            logger.error("Error en el login, no existe el usuario '" + username + "' en el sistema " + e.status());
        }

        if (usuarioVO.isEmpty()) {
            logger.error("Error en el login, no existe el usuario '" + username + "' en el sistema");
            throw new UsernameNotFoundException("Error");
        }

        UsuarioVO user = objectMapper.convertValue(usuarioVO.get().getDetalle(), UsuarioVO.class);

        List<GrantedAuthority> grantedAuthorities = user.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(simpleGrantedAuthority -> logger.info("Role: " + simpleGrantedAuthority.getAuthority()))
                .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), user.getEstatus(), true,
                true, true, grantedAuthorities);
    }

    @Override
    public Optional<ApiResponse> findByUsername(String name) {
        return usuarioClientRest.findByUsername(name);
    }
}
