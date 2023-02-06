package com.rrpvm.authtesh.di;

import com.rrpvm.authtesh.data.repository.AuthRepositoryImpl;
import com.rrpvm.authtesh.data.repository.UserRepositoryImpl;
import com.rrpvm.authtesh.domain.repository.AuthRepository;
import com.rrpvm.authtesh.domain.repository.UserRepository;


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
    @Singleton
    //@ViewModelScoped
    public abstract AuthRepository bindAuthRepository(AuthRepositoryImpl authRepository);

    //@ViewModelScoped
    @Binds
    @Singleton
    //@ViewModelScoped
    public abstract UserRepository bindUserRepository(UserRepositoryImpl authRepository);

}
