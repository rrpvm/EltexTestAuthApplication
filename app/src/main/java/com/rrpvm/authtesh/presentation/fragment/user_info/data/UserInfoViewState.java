package com.rrpvm.authtesh.presentation.fragment.user_info.data;

import com.rrpvm.authtesh.domain.model.UserInfoModel;


//1 поле,да,было бы логичнее сделать просто лив дату на модельку, но выдерживаем код стайл
public class UserInfoViewState {
    public final UserInfoModel userInfo;

    public UserInfoViewState(UserInfoModel userInfo) {
        this.userInfo = userInfo;
    }
}
