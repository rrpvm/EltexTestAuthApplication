package com.rrpvm.authtesh.data.network.sub_data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class HouseShortDataDto implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.houseId);
        dest.writeString(this.title);
        dest.writeString(this.level);
    }
    protected HouseShortDataDto(Parcel in) {
        this.houseId = in.readString();
        this.title = in.readString();
        this.level = in.readString();
    }

    public static final Parcelable.Creator<HouseShortDataDto> CREATOR = new Parcelable.Creator<HouseShortDataDto>() {
        @Override
        public HouseShortDataDto createFromParcel(Parcel source) {
            return new HouseShortDataDto(source);
        }

        @Override
        public HouseShortDataDto[] newArray(int size) {
            return new HouseShortDataDto[size];
        }
    };
}
