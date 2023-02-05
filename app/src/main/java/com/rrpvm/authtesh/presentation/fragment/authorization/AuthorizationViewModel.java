package com.rrpvm.authtesh.presentation.fragment.authorization;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rrpvm.authtesh.data.network.dto.GetTokenDto;
import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.usecase.GetTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.SetCurrentTokenUseCase;
import com.rrpvm.authtesh.presentation.fragment.authorization.data.AuthorizationViewEffect;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;
import com.rrpvm.authtesh.presentation.fragment.authorization.data.AuthorizationViewState;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Single;

@HiltViewModel
public class AuthorizationViewModel extends ViewModel {
    private final GetTokenUseCase getTokenUseCase;
    private final SetCurrentTokenUseCase setCurrentTokenUseCase;
    private Disposable getTokenJob;
    private MutableLiveData<AuthorizationViewState> mViewState = new MutableLiveData<>(new AuthorizationViewState());
    private MutableLiveData<AuthorizationViewEffect> mViewEffects = new MutableLiveData<>(new AuthorizationViewEffect.InitState());

    public LiveData<AuthorizationViewState> getViewState() {
        return mViewState;
    }

    public LiveData<AuthorizationViewEffect> getViewEffects() {
        return mViewEffects;
    }

    @Inject
    public AuthorizationViewModel(GetTokenUseCase getTokenUseCase, SetCurrentTokenUseCase setCurrentTokenUseCase) {
        this.getTokenUseCase = getTokenUseCase;
        this.setCurrentTokenUseCase = setCurrentTokenUseCase;
    }

    public void onUsernameInput(String newUsername) {
        if (this.mViewState.getValue() == null) return;
        this.mViewState.setValue(this.mViewState.getValue().setUsername(newUsername));
    }

    public void onPasswordInput(String newPassword) {
        if (this.mViewState.getValue() == null) return;
        this.mViewState.setValue(this.mViewState.getValue().setPassword(newPassword));
    }

    public void onLogInPressed() {
        AuthorizationViewState state = mViewState.getValue();
        if (state == null) return;
        if (getTokenJob != null && !getTokenJob.isDisposed()) getTokenJob.dispose();
        getTokenJob = Single.fromCallable(() -> {
            return getTokenUseCase.invoke(state.mUsername, state.mPassword);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getTokenDtoResource -> {
            if (getTokenDtoResource instanceof Resource.ResourceFailed) {
                mViewState.postValue(state.setLastError(true));
                mViewEffects.setValue(new AuthorizationViewEffect.ShowText(((Resource.ResourceFailed<GetTokenDto>) getTokenDtoResource).getUiText()));
            } else if (getTokenDtoResource instanceof Resource.ResourceSuccess) {
                mViewEffects.setValue(new AuthorizationViewEffect.AuthenticationSuccess());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (getTokenJob != null) {
            if (!getTokenJob.isDisposed()) getTokenJob.dispose();
        }
    }
}
