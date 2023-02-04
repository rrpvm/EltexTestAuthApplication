package com.rrpvm.authtesh.app;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.rrpvm.authtesh.di.AppComponent;
//import com.rrpvm.authtesh.di.DaggerAppComponent;

import dagger.hilt.android.HiltAndroidApp;


@HiltAndroidApp
public class EltexApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // AppComponent applicationComponent = DaggerAppComponent.builder().build();
        Hawk.init(this).build();
    }
}
