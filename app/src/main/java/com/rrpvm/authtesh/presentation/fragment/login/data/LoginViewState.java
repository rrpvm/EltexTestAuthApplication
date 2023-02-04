package com.rrpvm.authtesh.presentation.fragment.login.data;

import static com.rrpvm.authtesh.domain.utils.Constants.EMPTY_STRING;

import com.rrpvm.authtesh.domain.utils.Constants;

public class LoginViewState {
    private Boolean bInLoading = true;
    private String mUsername = EMPTY_STRING;
    private String mPassword = EMPTY_STRING;

    public LoginViewState(Boolean bInLoading, String mUsername, String mPassword) {
        this.bInLoading = bInLoading;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public LoginViewState() {

    }
    public LoginViewState(Boolean bInLoading) {
        this.bInLoading = bInLoading;
    }
    public Boolean getInLoadingState() {
        return bInLoading;
    }

    public void setInLoadingState(Boolean bInLoading) {
        this.bInLoading = bInLoading;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
