package com.rrpvm.authtesh.di;

import javax.inject.Singleton;
import dagger.Component;

@Component(modules = {NetworkModule.class})
@Singleton
public interface AppComponent {
}
