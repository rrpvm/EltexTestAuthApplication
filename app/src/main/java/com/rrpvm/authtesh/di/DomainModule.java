package com.rrpvm.authtesh.di;

import com.rrpvm.authtesh.domain.repository.AuthRepository;
import com.rrpvm.authtesh.domain.usecase.GetCurrentTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.GetTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.SetCurrentTokenUseCase;

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

    @Provides
    public GetCurrentTokenUseCase provideGetCurrentTokenUseCase(AuthRepository authRepository) {
        return new GetCurrentTokenUseCase(authRepository);
    }

    @Provides
    public SetCurrentTokenUseCase provideSetCurrentTokenUseCase(AuthRepository authRepository) {
        return new SetCurrentTokenUseCase(authRepository);
    }
}
