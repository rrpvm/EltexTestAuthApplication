package com.rrpvm.authtesh.presentation.fragment.authorization;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rrpvm.authtesh.R;
import com.rrpvm.authtesh.databinding.FragmentAuthorizationBinding;
import com.rrpvm.authtesh.domain.helpers.EditTextHelper;
import com.rrpvm.authtesh.domain.helpers.ToastHelper;
import com.rrpvm.authtesh.presentation.fragment.authorization.data.AuthorizationViewEffect;
import com.rrpvm.authtesh.presentation.fragment.authorization.data.AuthorizationViewState;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthorizationFragment extends Fragment {
    @Nullable
    private FragmentAuthorizationBinding authorizationBinding = null;
    private AuthorizationViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AuthorizationViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        authorizationBinding = FragmentAuthorizationBinding.inflate(inflater, container, false);
        return authorizationBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (authorizationBinding == null) return;
        subscribeListeners();
        setupUi();

        // authorizationBinding.tilUsername
    }

    private void setupUi() {
        AuthorizationViewState state = viewModel.getViewState().getValue();
        if (state != null) {
            authorizationBinding.tilPassword.getEditText().setText(state.mPassword);
            authorizationBinding.tilUsername.getEditText().setText(state.mUsername);
        }
        EditTextHelper.onTextChanged(authorizationBinding.tilUsername.getEditText(), s -> {
            viewModel.onUsernameInput(s);
            return null;
        });
        EditTextHelper.onTextChanged(authorizationBinding.tilPassword.getEditText(), s -> {
            viewModel.onPasswordInput(s);
            return null;
        });
        authorizationBinding.btnLogin.setOnClickListener(v -> {
            viewModel.onLogInPressed();
        });
    }

    private void subscribeListeners() {
        viewModel.getViewState().observe(this.getViewLifecycleOwner(), viewState -> {
            if (viewState == null) return;
            if (viewState.bLastAttemptFailed) {
                authorizationBinding.tilPassword.setError(getString(R.string.wrong_credentials));
                authorizationBinding.tilUsername.setError(getString(R.string.wrong_credentials));
            } else {
                authorizationBinding.tilPassword.setError(null);
                authorizationBinding.tilUsername.setError(null);
            }
            authorizationBinding.btnLogin.setEnabled(!viewState.bInLoading);
            if (!viewState.bInLoading) {
                authorizationBinding.btnLogin.setBackgroundColor(getContext().getColor(R.color.mainUiColor));
            } else
                authorizationBinding.btnLogin.setBackgroundColor(Color.parseColor("#805d2ed5"));//hard code, но у меня уже просто нет времени на вынесение в ресурсы и тд
        });
        viewModel.getViewEffects().observe(this.getViewLifecycleOwner(), loginViewEffect -> {
            if (loginViewEffect instanceof AuthorizationViewEffect.AuthenticationSuccess) {
                Toast.makeText(getContext(), "success", Toast.LENGTH_LONG).show();
            } else if (loginViewEffect instanceof AuthorizationViewEffect.ShowText) {
                ToastHelper.showText(((AuthorizationViewEffect.ShowText) loginViewEffect).uiText, getContext());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        authorizationBinding = null;
    }
}
