package com.rrpvm.authtesh.data.network.dto;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.rrpvm.authtesh.data.network.sub_data.HouseShortDataDto;
import com.rrpvm.authtesh.domain.model.UserInfoModel;

import java.util.List;
import java.util.Map;

//я так подумал,полей приходит много и многие вообще не используются в контексте проекта, поэтому дтошка будет не полная
public class GetUserInfoDto {
    @SerializedName("roleId")
    public final String roleId;
    @SerializedName("username")
    public final String username;
    @Nullable
    @SerializedName("email")
    public final String email;
    @SerializedName("access")
    public final Map<String, HouseShortDataDto> access;
    @SerializedName("ownedHouseIds")
    public final List<String> ownedHouseIds;

    public GetUserInfoDto(String roleId, String username, @Nullable String email, Map<String, HouseShortDataDto> access, List<String> ownedHouseIds) {
        this.roleId = roleId;
        this.username = username;
        this.email = email;
        this.access = access;
        this.ownedHouseIds = ownedHouseIds;
    }

    public UserInfoModel toUserInfo() {
        return new UserInfoModel(roleId, username, email, access, ownedHouseIds);
    }
}
