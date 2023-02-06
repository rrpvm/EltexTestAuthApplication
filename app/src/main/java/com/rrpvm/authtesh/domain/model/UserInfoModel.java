package com.rrpvm.authtesh.domain.model;

import androidx.annotation.Nullable;

import com.rrpvm.authtesh.data.network.sub_data.HouseShortDataDto;

import java.util.List;
import java.util.Map;

public class UserInfoModel {
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
}
