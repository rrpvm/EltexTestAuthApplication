package com.rrpvm.authtesh.domain.usecase;

import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.TokenModel;
import com.rrpvm.authtesh.domain.repository.AuthRepository;

import javax.inject.Inject;

public class GetTokenUseCase {
    private final AuthRepository repository;

    @Inject
    public GetTokenUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public Resource<TokenModel> invoke(String name, String password) {
        return repository.getToken(name, password);
    }
}
