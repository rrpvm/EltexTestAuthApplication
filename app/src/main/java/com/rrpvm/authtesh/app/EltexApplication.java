package com.rrpvm.authtesh.app;

import android.app.Application;

import com.rrpvm.authtesh.di.AppComponent;
import com.rrpvm.authtesh.di.DaggerAppComponent;


public class EltexApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent applicationComponent = DaggerAppComponent.builder().build();
    }
}
