package com.rrpvm.authtesh.data.network.sub_data;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class HouseShortDataDto {
    @SerializedName("houseId")
    @Nullable
    public final String houseId;
    @SerializedName("title")
    public final String title;
    @SerializedName("level")
    public final String level;

    public HouseShortDataDto(String houseId, String title, String level) {
        this.houseId = houseId;
        this.title = title;
        this.level = level;
    }
}
