package com.rrpvm.authtesh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rrpvm.authtesh.di.ApplicationComponent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}