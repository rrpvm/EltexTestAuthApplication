package com.rrpvm.authtesh.presentation.fragment.user_info;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.rrpvm.authtesh.data.network.sub_data.HouseShortDataDto;
import com.rrpvm.authtesh.databinding.FragmentUserInfoBinding;
import com.rrpvm.authtesh.domain.entity.common.UiText;
import com.rrpvm.authtesh.domain.helpers.TextViewsHelper;
import com.rrpvm.authtesh.domain.helpers.ToastHelper;
import com.rrpvm.authtesh.presentation.fragment.user_info.data.UserInfoViewEffect;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserInfoFragment extends Fragment {
    private FragmentUserInfoBinding binding;
    private UserInfoViewModel viewModel;
    private HousesAdapter adapter;

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
        adapter = new HousesAdapter();
        adapter.setOnItemClickListener(onHouseItemClick());
        binding.rvHouses.setAdapter(adapter);
        viewModel.getViewState().observe(this.getViewLifecycleOwner(), state -> {
            if (state.userInfo == null) {
                TextViewsHelper.setTextValueOrEmpty(binding.tvEmail.getEditText(), null);
                TextViewsHelper.setTextValueOrEmpty(binding.tvUsername.getEditText(), null);
                TextViewsHelper.setTextValueOrEmpty(binding.tvRoleId.getEditText(), null);
            } else {
                TextViewsHelper.setTextValueOrEmpty(binding.tvEmail.getEditText(), state.userInfo.email);
                TextViewsHelper.setTextValueOrEmpty(binding.tvUsername.getEditText(), state.userInfo.username);
                TextViewsHelper.setTextValueOrEmpty(binding.tvRoleId.getEditText(), state.userInfo.roleId);
                List<HouseShortDataDto> list = state.userInfo.ownedHouseIds.stream().map(it -> {
                    HouseShortDataDto pure = state.userInfo.access.get(it);
                    if (pure == null) return null;
                    return new HouseShortDataDto(it, pure.title, pure.level);
                }).filter(Objects::nonNull).collect(Collectors.toList());
                adapter.setData(list);
            }
        });
        viewModel.getViewEffect().observe(this.getViewLifecycleOwner(), effect -> {
            if (effect instanceof UserInfoViewEffect.InitViewEffect) return;
            if (effect instanceof UserInfoViewEffect.GoHouseScreenViewEffect) {
                actionGoHouseScreen(((UserInfoViewEffect.GoHouseScreenViewEffect) effect).houseId);
                viewModel.clearEffects();
            } else if (effect instanceof UserInfoViewEffect.ShowText) {
                ToastHelper.showText(((UserInfoViewEffect.ShowText) effect).uiText, getContext());
                viewModel.clearEffects();
            }
        });
    }

    public HousesAdapter.HouseAdapterCallbacks.ItemClickListener onHouseItemClick() {
        return houseId -> {
            viewModel.onHouseClick(houseId);
        };
    }

    private void actionGoHouseScreen(String houseId) {
        NavHostFragment.findNavController(this).navigate(UserInfoFragmentDirections.actionFragmentUserScreenToFramgnetHouseScreen(houseId));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        adapter = null;
    }
}
