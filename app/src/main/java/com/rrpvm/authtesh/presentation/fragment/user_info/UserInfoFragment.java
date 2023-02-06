package com.rrpvm.authtesh.presentation.fragment.user_info;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rrpvm.authtesh.databinding.FragmentUserInfoBinding;
import com.rrpvm.authtesh.domain.helpers.TextViewsHelper;
import com.rrpvm.authtesh.presentation.fragment.authorization.AuthorizationViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserInfoFragment extends Fragment {
    private FragmentUserInfoBinding binding;
    private UserInfoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UserInfoViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (binding == null) return;
        viewModel.onInitialize();
        viewModel.getViewState().observe(this.getViewLifecycleOwner(), state -> {
            if (state.userInfo == null) {
                TextViewsHelper.setTextValueOrEmpty(binding.tvEmail.getEditText(), null);
                TextViewsHelper.setTextValueOrEmpty(binding.tvUsername.getEditText(), null);
                TextViewsHelper.setTextValueOrEmpty(binding.tvRoleId.getEditText(), null);
            } else {
                TextViewsHelper.setTextValueOrEmpty(binding.tvEmail.getEditText(), state.userInfo.email);
                TextViewsHelper.setTextValueOrEmpty(binding.tvUsername.getEditText(), state.userInfo.username);
                TextViewsHelper.setTextValueOrEmpty(binding.tvRoleId.getEditText(), state.userInfo.roleId);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
