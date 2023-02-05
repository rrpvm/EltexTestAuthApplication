package com.rrpvm.authtesh.domain.repository;


import com.rrpvm.authtesh.data.network.dto.GetTokenDto;
import com.rrpvm.authtesh.domain.entity.network.Resource;

import java.util.Optional;

import javax.inject.Singleton;

@Singleton
public interface AuthRepository {
    //String -> immutable -> store in memory while gc doesn't destroy it -> password is unsecure, but who cares about it in android :)
    Resource<GetTokenDto> getToken(String username, String password);

    Optional<String> getCurrentToken();
    void setCurrentToken(String token);
}
