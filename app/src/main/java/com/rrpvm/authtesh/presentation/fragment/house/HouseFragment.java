package com.rrpvm.authtesh.presentation.fragment.house;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.rrpvm.authtesh.databinding.FragmentHouseBinding;
import com.rrpvm.authtesh.domain.helpers.ToastHelper;
import com.rrpvm.authtesh.presentation.fragment.house.data.HouseFragmentViewEffect;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HouseFragment extends Fragment {
    private FragmentHouseBinding binding = null;
    private HouseViewModel viewModel = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HouseViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHouseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (binding == null) return;
        viewModel.onInitialize();
        viewModel.getViewEffects().observe(this.getViewLifecycleOwner(), effect -> {
            if (effect instanceof HouseFragmentViewEffect.InitViewEffect) return;
            if (effect instanceof HouseFragmentViewEffect.ShowText) {
                ToastHelper.showText(((HouseFragmentViewEffect.ShowText) effect).uiText, getContext());
            } else if (effect instanceof HouseFragmentViewEffect.BackToTheScreen) {
                onGoBackScreen();
            }
            viewModel.clearEffects();
        });
        viewModel.getViewState().observe(this.getViewLifecycleOwner(), state -> {
            if (state == null) return;
            binding.edAddress.setText(state.mAddress);
            binding.edId.setText(state.mId);
            binding.timeZone.setText(state.mTimeZone);
        });
       /* Fragment fragment = this;
        requireActivity().getOnBackPressedDispatcher().addCallback(this.getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(fragment).popBackStack();
            }
        });*/
    }

    private void onGoBackScreen() {
        NavHostFragment.findNavController(this).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
