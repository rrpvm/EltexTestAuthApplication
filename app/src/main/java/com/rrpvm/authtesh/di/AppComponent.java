package com.rrpvm.authtesh.di;


import dagger.Component;
import dagger.hilt.DefineComponent;
import dagger.hilt.components.SingletonComponent;
@DefineComponent(parent =  SingletonComponent.class)
@Component(modules = {DataModule.class, DomainModule.class, NetworkModule.class})
public interface AppComponent {
}
