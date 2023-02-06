package com.rrpvm.authtesh.data.network.dto;

import com.google.gson.annotations.SerializedName;
import com.rrpvm.authtesh.domain.model.TokenModel;

public class GetTokenDto {
    @SerializedName("access_token")
    public final String mAccessToken;
    @SerializedName("token_type")
    public final String mTokenType;
    @SerializedName("expires_in")
    public final String mExpiresIn;
    @SerializedName("scope")
    public final String mScope;

    public GetTokenDto(String mAccessToken, String mTokenType, String mExpiresIn, String mScope) {
        this.mAccessToken = mAccessToken;
        this.mTokenType = mTokenType;
        this.mExpiresIn = mExpiresIn;
        this.mScope = mScope;
    }

    public TokenModel toTokenModel() {
        return new TokenModel(mAccessToken, mTokenType, mExpiresIn, mScope);
    }
}
