package com.rrpvm.authtesh.presentation.fragment.authorization.data;

import static com.rrpvm.authtesh.domain.utils.Constants.EMPTY_STRING;

import android.util.Log;


import com.rrpvm.authtesh.BuildConfig;

public class AuthorizationViewState implements Cloneable {
    public final Boolean bInLoading;
    public final Boolean bLastAttemptFailed;
    public final String mUsername;
    public final String mPassword;

    public AuthorizationViewState(Boolean bInLoading, Boolean bLastAttemptFailed, String mUsername, String mPassword) {
        this.bInLoading = bInLoading;
        this.bLastAttemptFailed = bLastAttemptFailed;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public AuthorizationViewState() {
        bInLoading = true;
        bLastAttemptFailed = false;
        mUsername = EMPTY_STRING;
        mPassword = EMPTY_STRING;
    }

    public AuthorizationViewState changeInLoading(Boolean bInLoading) {
        return new AuthorizationViewState(bInLoading, bLastAttemptFailed, mUsername, mPassword);
    }

    public AuthorizationViewState setUsername(String mUsername) {
        return new AuthorizationViewState(bInLoading, false, mUsername, mPassword);
    }

    public AuthorizationViewState setPassword(String mPassword) {
        return new AuthorizationViewState(bInLoading, false, mUsername, mPassword);
    }

    public AuthorizationViewState setLastError(boolean bError) {
        return new AuthorizationViewState(bInLoading, bError, mUsername, mPassword);
    }

   /* public AuthorizationViewState copy() {
        try {
            return (AuthorizationViewState) this.clone();
        } catch (CloneNotSupportedException e) {
            Log.e(BuildConfig.APP_TAG, e.getLocalizedMessage());
            return this;
        }
    }*/
}
