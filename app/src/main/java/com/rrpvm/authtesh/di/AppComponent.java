package com.rrpvm.authtesh.di;

import javax.inject.Singleton;
import dagger.Component;
import dagger.hilt.DefineComponent;
import dagger.hilt.components.SingletonComponent;

//@Component(modules = {NetworkModule.class})
//@Singleton
@DefineComponent(parent = SingletonComponent.class)
public interface AppComponent {
}
