package com.rrpvm.authtesh.presentation.fragment.user_info.data;


import com.rrpvm.authtesh.domain.entity.common.UiText;

public abstract class UserInfoViewEffect {
    public static class GoHouseScreenViewEffect extends UserInfoViewEffect{
        public final String houseId;

        public GoHouseScreenViewEffect(String houseId) {
            this.houseId = houseId;
        }
    }
    public static class ShowText extends UserInfoViewEffect{
        public final UiText uiText;

        public ShowText(UiText uiText) {
            this.uiText = uiText;
        }
    }
    public static class InitViewEffect extends UserInfoViewEffect{
    }
}
