package com.rrpvm.authtesh.domain.usecase;

import com.rrpvm.authtesh.domain.repository.AuthRepository;

import java.util.Optional;

import javax.inject.Inject;

public class GetCurrentTokenUseCase {
    private final AuthRepository repository;
    @Inject
    public GetCurrentTokenUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    public Optional<String> invoke() {
        return repository.getCurrentToken();
    }
}
