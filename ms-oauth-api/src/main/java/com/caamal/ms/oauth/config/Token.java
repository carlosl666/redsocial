package com.caamal.ms.oauth.config;

import com.caamal.ms.oauth.service.remote.IUsuarioService;
import com.caamal.ms.oauth.service.remote.UsuarioService;
import com.caamal.ms.oauth.vo.ApiResponse;
import com.caamal.ms.oauth.vo.UsuarioVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class Token implements TokenEnhancer {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(Token.class);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> objectMap = new HashMap<>();
        Optional<ApiResponse> apiResponse = Optional.empty();
        UsuarioVO usuarioVO = null;
        try {
            apiResponse = usuarioService.findByUsername(oAuth2Authentication.getName());
        } catch (FeignException e) {
            logger.error("Error en el login, no existe el usuario '" + oAuth2Authentication.getName() + "' en el sistema " + e.status());
        }
        if(apiResponse.isPresent()){
            usuarioVO = objectMapper.convertValue(apiResponse.get().getDetalle(), UsuarioVO.class);
        }

        objectMap.put("username", usuarioVO.getUsername());
        objectMap.put("correo", usuarioVO.getEmail());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(objectMap);
        return oAuth2AccessToken;
    }
}