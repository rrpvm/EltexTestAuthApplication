package com.rrpvm.authtesh.domain.repository;


import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.TokenModel;

import java.util.Optional;

import javax.inject.Singleton;

@Singleton
public interface AuthRepository {
    //String -> immutable -> store in memory while gc doesn't destroy it -> password is unsecure, but who cares about it in android :)
    Resource<TokenModel> getToken(String username, String password);

    Optional<String> getCurrentToken();
    void setCurrentToken(String token);
}
