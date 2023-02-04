package com.rrpvm.authtesh.presentation.fragment.login.data;

public abstract class LoginViewEffect {
    public static class AuthenticationErrorEffect extends LoginViewEffect {
    }

    public static class AuthenticationSuccess extends LoginViewEffect {
    }

    public static class InitState extends LoginViewEffect {
    }
}
