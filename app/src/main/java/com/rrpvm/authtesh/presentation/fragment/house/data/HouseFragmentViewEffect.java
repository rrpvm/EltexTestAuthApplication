package com.rrpvm.authtesh.presentation.fragment.house.data;

import com.rrpvm.authtesh.domain.entity.common.UiText;

public abstract class HouseFragmentViewEffect {
    public static class ShowText extends HouseFragmentViewEffect {
        public final UiText uiText;

        public ShowText(UiText uiText) {
            this.uiText = uiText;
        }
    }

    public static class BackToTheScreen extends HouseFragmentViewEffect {

    }

    public static class InitViewEffect extends HouseFragmentViewEffect {
    }
}
