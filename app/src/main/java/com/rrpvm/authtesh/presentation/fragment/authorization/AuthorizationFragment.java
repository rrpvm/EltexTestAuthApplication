package com.rrpvm.authtesh.presentation.fragment.authorization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rrpvm.authtesh.R;
import com.rrpvm.authtesh.databinding.FragmentAuthorizationBinding;
import com.rrpvm.authtesh.databinding.FragmentLoginBinding;

public class AuthorizationFragment extends Fragment {

    @Nullable
    private FragmentAuthorizationBinding authorizationBinding = null;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        authorizationBinding = null;
    }
}
