package com.rrpvm.authtesh.di;

import com.rrpvm.authtesh.data.repository.AuthRepositoryImpl;
import com.rrpvm.authtesh.domain.repository.AuthRepository;


import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class DataModule {
    @Binds
    //@ViewModelScoped
    @Singleton
    public abstract AuthRepository bindAuthRepository(AuthRepositoryImpl authRepository);
}
