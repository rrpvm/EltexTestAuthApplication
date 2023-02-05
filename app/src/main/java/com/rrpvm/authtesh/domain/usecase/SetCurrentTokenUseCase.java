package com.rrpvm.authtesh.domain.usecase;

import com.rrpvm.authtesh.domain.repository.AuthRepository;

import javax.inject.Inject;

public class SetCurrentTokenUseCase {
    private final AuthRepository repository;

    @Inject
    public SetCurrentTokenUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public void invoke(String token) {
        repository.setCurrentToken(token);
    }
}
