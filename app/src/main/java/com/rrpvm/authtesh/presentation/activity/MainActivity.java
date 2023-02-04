package com.rrpvm.authtesh.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rrpvm.authtesh.R;
import com.rrpvm.authtesh.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }
}