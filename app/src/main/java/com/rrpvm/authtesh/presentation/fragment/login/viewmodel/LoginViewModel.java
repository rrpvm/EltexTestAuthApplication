package com.rrpvm.authtesh.presentation.fragment.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.rrpvm.authtesh.data.network.dto.GetTokenDto;
import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.usecase.GetCurrentTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.GetTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.SetCurrentTokenUseCase;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewState;

import java.util.Optional;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final GetTokenUseCase getTokenUseCase;
    private final GetCurrentTokenUseCase getCurrentTokenUseCase;
    private final SetCurrentTokenUseCase setCurrentTokenUseCase;
    private MutableLiveData<LoginViewState> mViewState = new MutableLiveData<>(new LoginViewState());
    private MutableLiveData<LoginViewEffect> mViewEffects = new MutableLiveData<>(new LoginViewEffect.InitState());

    @Inject
    public LoginViewModel(SavedStateHandle savedStateHandle, GetTokenUseCase getTokenUseCase, GetCurrentTokenUseCase getCurrentTokenUseCase, SetCurrentTokenUseCase setCurrentTokenUseCase) {
        this.getTokenUseCase = getTokenUseCase;
        this.getCurrentTokenUseCase = getCurrentTokenUseCase;
        this.setCurrentTokenUseCase = setCurrentTokenUseCase;
    }
    public void onInit() {
        new Thread(() -> {
            try {
                Optional<String> token = getCurrentTokenUseCase.invoke();
                if (token.isPresent()) {

                } else {
                    LoginViewState loginViewState = mViewState.getValue();
                    if (loginViewState == null) {
                        return;
                    } else {
                        mViewEffects.postValue(new LoginViewEffect.AuthenticationErrorEffect());
                       /* Resource<GetTokenDto> rToken = getTokenUseCase.invoke(loginViewState.getUsername(), loginViewState.getPassword());
                        if (rToken instanceof Resource.ResourceSuccess) {
                            setCurrentTokenUseCase.invoke(((Resource.ResourceSuccess<GetTokenDto>) rToken).getData().mAccessToken);
                        } else if (rToken instanceof Resource.ResourceFailed) {
                            mViewEffects.postValue(new LoginViewEffect.AuthenticationErrorEffect(((Resource.ResourceFailed<GetTokenDto>) rToken).getUiText()));
                        }*/
                    }
                }
                Thread.sleep(2500L);
                mViewState.postValue(new LoginViewState(false));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public LiveData<LoginViewState> getViewState() {
        return mViewState;
    }

    public LiveData<LoginViewEffect> getViewEffects() {
        return mViewEffects;
    }
}
