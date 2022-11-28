package com.caamal.ms.oauth.service.remote;

import com.caamal.ms.oauth.vo.ApiResponse;

import java.util.Optional;

public interface IUsuarioService {
    Optional<ApiResponse> findByUsername(String name);
}
