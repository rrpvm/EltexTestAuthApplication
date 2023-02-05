package com.rrpvm.authtesh.presentation.fragment.authorization.data;

import com.rrpvm.authtesh.domain.entity.common.UiText;

public abstract class AuthorizationViewEffect {

    public static class AuthenticationErrorEffect extends AuthorizationViewEffect {
    }

    public static class ShowText extends AuthorizationViewEffect {
        public final UiText uiText;
        public ShowText(UiText uiText) {
            this.uiText = uiText;
        }

    }

    public static class AuthenticationSuccess extends AuthorizationViewEffect {

    }
    public static class InitState extends AuthorizationViewEffect {
    }
}