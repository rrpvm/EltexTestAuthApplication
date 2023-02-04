package com.rrpvm.authtesh.presentation.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rrpvm.authtesh.databinding.FragmentLoginBinding;
import com.rrpvm.authtesh.presentation.fragment.login.viewmodel.LoginViewModel;


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
        viewModel.getViewState().observe(this.getViewLifecycleOwner(), loginViewState -> {
            if (!loginViewState.getInLoadingState())
                loginFragmentBinding.groupLoading.setVisibility(View.INVISIBLE);
        });
        viewModel.getViewEffects().observe(this.getViewLifecycleOwner(), loginViewEffect -> {

        });
        viewModel.onInit();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginFragmentBinding = null;
    }

}
