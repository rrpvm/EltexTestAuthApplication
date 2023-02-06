package com.rrpvm.authtesh.domain.model;


public class TokenModel {
    public final String mAccessToken;
    public final String mTokenType;
    public final String mExpiresIn;
    public final String mScope;

    public TokenModel(String mAccessToken, String mTokenType, String mExpiresIn, String mScope) {
        this.mAccessToken = mAccessToken;
        this.mTokenType = mTokenType;
        this.mExpiresIn = mExpiresIn;
        this.mScope = mScope;
    }
}
