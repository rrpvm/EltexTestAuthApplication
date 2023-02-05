package com.rrpvm.authtesh.presentation.fragment.login.data;

import com.rrpvm.authtesh.domain.entity.common.UiText;

public abstract class LoginViewEffect {
    public static class AuthenticationErrorEffect extends LoginViewEffect {
    }

    public static class ShowText extends LoginViewEffect {
        public final UiText uiText;

        public ShowText(UiText uiText) {
            this.uiText = uiText;
        }

    }

    public static class AuthenticationSuccess extends LoginViewEffect {
    }

    public static class InitState extends LoginViewEffect {
    }
}
