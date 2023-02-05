package com.rrpvm.authtesh.presentation.fragment.authorization;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rrpvm.authtesh.data.network.dto.GetTokenDto;
import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.usecase.GetTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.SetCurrentTokenUseCase;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;
import com.rrpvm.authtesh.presentation.fragment.authorization.data.AuthorizationViewState;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AuthorizationViewModel extends ViewModel {
    private final GetTokenUseCase getTokenUseCase;
    private final SetCurrentTokenUseCase setCurrentTokenUseCase;
    private MutableLiveData<AuthorizationViewState> mViewState = new MutableLiveData<>(new AuthorizationViewState());
    private MutableLiveData<LoginViewEffect> mViewEffects = new MutableLiveData<>(new LoginViewEffect.InitState());

    public LiveData<AuthorizationViewState> getViewState() {
        return mViewState;
    }

    public LiveData<LoginViewEffect> getViewEffects() {
        return mViewEffects;
    }

    @Inject
    public AuthorizationViewModel(GetTokenUseCase getTokenUseCase, SetCurrentTokenUseCase setCurrentTokenUseCase) {
        this.getTokenUseCase = getTokenUseCase;
        this.setCurrentTokenUseCase = setCurrentTokenUseCase;
    }

    public void onUsernameInput(String newUsername) {
        if (this.mViewState.getValue() == null) return;
        this.mViewState.setValue(this.mViewState.getValue().setUsername(newUsername).copy());
    }

    public void onPasswordInput(String newPassword) {
        if (this.mViewState.getValue() == null) return;
        this.mViewState.setValue(this.mViewState.getValue().setPassword(newPassword).copy());
    }

    public void onLogInPressed() {
        AuthorizationViewState state = mViewState.getValue();
        if (state == null) return;
        Resource<GetTokenDto> resource = getTokenUseCase.invoke(state.mUsername, state.mPassword);
        if (resource instanceof Resource.ResourceFailed) {
            mViewEffects.setValue(new LoginViewEffect.ShowText(((Resource.ResourceFailed<GetTokenDto>) resource).getUiText()));
            mViewEffects.setValue(new LoginViewEffect.AuthenticationErrorEffect());
        } else if (resource instanceof Resource.ResourceSuccess) {
            mViewEffects.setValue(new LoginViewEffect.AuthenticationSuccess());
        }
    }
}
