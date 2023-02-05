package com.rrpvm.authtesh.presentation.fragment.authorization.data;

import static com.rrpvm.authtesh.domain.utils.Constants.EMPTY_STRING;

import android.util.Log;


import com.rrpvm.authtesh.BuildConfig;

public class AuthorizationViewState implements Cloneable {
    public Boolean bInLoading = true;
    public String mUsername = EMPTY_STRING;
    public String mPassword = EMPTY_STRING;

    public AuthorizationViewState(Boolean bInLoading, String mUsername, String mPassword) {
        this.bInLoading = bInLoading;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public AuthorizationViewState() {

    }
    public AuthorizationViewState setInLoading(Boolean bInLoading) {
        this.bInLoading = bInLoading;
        return this;
    }

    public AuthorizationViewState setUsername(String mUsername) {
        this.mUsername = mUsername;
        return this;
    }

    public AuthorizationViewState setPassword(String mPassword) {
        this.mPassword = mPassword;
        return this;
    }

    public AuthorizationViewState copy() {
        try {
            return (AuthorizationViewState) this.clone();
        } catch (CloneNotSupportedException e) {
            Log.e(BuildConfig.APP_TAG, e.getLocalizedMessage());
            return this;
        }
    }
}
