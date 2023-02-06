package com.rrpvm.authtesh.presentation.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.rrpvm.authtesh.databinding.FragmentLoginBinding;
import com.rrpvm.authtesh.domain.model.UserInfoModel;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    @Nullable
    private FragmentLoginBinding loginFragmentBinding = null;
    @Nullable
    private LoginViewModel viewModel = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginFragmentBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return loginFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (loginFragmentBinding == null || viewModel == null) return;
        viewModel.getViewEffects().observe(this.getViewLifecycleOwner(), loginViewEffect -> {
            if (loginViewEffect instanceof LoginViewEffect.AuthenticationErrorEffect) {
                actionGoAuthorizationScreen();
            }
            if (loginViewEffect instanceof LoginViewEffect.AuthenticationSuccess) {
                actionGoUserScreen(((LoginViewEffect.AuthenticationSuccess) loginViewEffect).userInfoModel);
            }
        });
        viewModel.onInit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginFragmentBinding = null;
    }

    private void actionGoUserScreen(UserInfoModel userInfoModel) {
        NavHostFragment.findNavController(this).navigate(LoginFragmentDirections.actionFragmentLoginStartToFragmentUserScreen(userInfoModel));
    }

    private void actionGoAuthorizationScreen() {
        NavHostFragment.findNavController(this).navigate(LoginFragmentDirections.fragmentLoginToAuthorize());
    }
}
