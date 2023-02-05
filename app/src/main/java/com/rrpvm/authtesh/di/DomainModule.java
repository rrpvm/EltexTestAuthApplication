package com.rrpvm.authtesh.di;

import com.rrpvm.authtesh.domain.repository.AuthRepository;
import com.rrpvm.authtesh.domain.usecase.GetTokenUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;

@Module
@InstallIn(AppComponent.class)
public class DomainModule {

    @Provides
    public GetTokenUseCase provideTokenUseCase(AuthRepository authRepository) {
        return new GetTokenUseCase(authRepository);
    }
}
