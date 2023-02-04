package com.rrpvm.authtesh.app;

import android.app.Application;

import com.rrpvm.authtesh.di.ApplicationComponent;

import dagger.android.DaggerApplication;


public class EltexApplication extends Application {

    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = 
    }
}
