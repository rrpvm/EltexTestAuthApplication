package com.rrpvm.authtesh.data.network.dto;

import com.google.gson.annotations.SerializedName;
import com.rrpvm.authtesh.domain.model.HouseModel;

public class GetHouseInfoDto {
    @SerializedName("id")
    public final String id;
    @SerializedName("address")
    public final String address;
    @SerializedName("timeZone")
    public final String timeZone;

    public GetHouseInfoDto(String id, String address, String timeZone) {
        this.id = id;
        this.address = address;
        this.timeZone = timeZone;
    }

    public HouseModel toDomain() {
        return new HouseModel(id, address, timeZone);
    }
}
