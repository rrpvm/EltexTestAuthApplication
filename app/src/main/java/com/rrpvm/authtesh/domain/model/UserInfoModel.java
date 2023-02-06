package com.rrpvm.authtesh.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.rrpvm.authtesh.data.network.sub_data.HouseShortDataDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoModel implements Parcelable {
    public final String roleId;
    public final String username;
    @Nullable
    public final String email;
    public final Map<String, HouseShortDataDto> access;
    public final List<String> ownedHouseIds;

    public UserInfoModel(String roleId, String username, @Nullable String email, Map<String, HouseShortDataDto> access, List<String> ownedHouseIds) {
        this.roleId = roleId;
        this.username = username;
        this.email = email;
        this.access = access;
        this.ownedHouseIds = ownedHouseIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.roleId);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeInt(this.access.size());
        for (Map.Entry<String, HouseShortDataDto> entry : this.access.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
        dest.writeStringList(this.ownedHouseIds);
    }

    protected UserInfoModel(Parcel in) {
        this.roleId = in.readString();
        this.username = in.readString();
        this.email = in.readString();
        int accessSize = in.readInt();
        this.access = new HashMap<String, HouseShortDataDto>(accessSize);
        for (int i = 0; i < accessSize; i++) {
            String key = in.readString();
            HouseShortDataDto value = in.readParcelable(HouseShortDataDto.class.getClassLoader());
            this.access.put(key, value);
        }
        this.ownedHouseIds = in.createStringArrayList();
    }

    public static final Parcelable.Creator<UserInfoModel> CREATOR = new Parcelable.Creator<UserInfoModel>() {
        @Override
        public UserInfoModel createFromParcel(Parcel source) {
            return new UserInfoModel(source);
        }

        @Override
        public UserInfoModel[] newArray(int size) {
            return new UserInfoModel[size];
        }
    };
}
