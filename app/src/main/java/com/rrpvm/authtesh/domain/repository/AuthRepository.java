package com.rrpvm.authtesh.domain.repository;

import com.rrpvm.authtesh.domain.entity.network.Resource;

import javax.inject.Singleton;

@Singleton
public interface AuthRepository {
    //String -> immutable -> store in memory while gc doesn't destroy it -> password is unsecure
    Resource<String> getToken(String username, char[] password);
}
