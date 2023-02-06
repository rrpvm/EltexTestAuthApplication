package com.rrpvm.authtesh.domain.model;

import com.google.gson.annotations.SerializedName;

public class HouseModel {
    public final String mId;
    public final String mAddress;
    public final String mTimeZone;

    public HouseModel(String mId, String mAddress, String mTimeZone) {
        this.mId = mId;
        this.mAddress = mAddress;
        this.mTimeZone = mTimeZone;
    }
}
