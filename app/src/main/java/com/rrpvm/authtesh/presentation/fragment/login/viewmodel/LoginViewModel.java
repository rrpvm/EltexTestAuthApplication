package com.rrpvm.authtesh.presentation.fragment.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.orhanobut.hawk.Hawk;
import com.rrpvm.authtesh.data.network.dto.GetTokenDto;
import com.rrpvm.authtesh.data.repository.AuthRepositoryImpl;
import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.repository.AuthRepository;
import com.rrpvm.authtesh.domain.usecase.GetCurrentTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.GetTokenUseCase;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewState;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private GetTokenUseCase getTokenUseCase;
    private GetCurrentTokenUseCase getCurrentTokenUseCase;
    private MutableLiveData<LoginViewState> mViewState = new MutableLiveData<>(new LoginViewState());
    private MutableLiveData<LoginViewEffect> mViewEffects = new MutableLiveData<>(new LoginViewEffect.InitState());

    @Inject
    public LoginViewModel(SavedStateHandle savedStateHandle, GetTokenUseCase getTokenUseCase, GetCurrentTokenUseCase getCurrentTokenUseCase) {
        this.getTokenUseCase = getTokenUseCase;
        this.getCurrentTokenUseCase = getCurrentTokenUseCase;
    }

    public void onInit() {
        new Thread(() -> {
            try {
                if (null != null) {

                } else {
                    LoginViewState loginViewState = mViewState.getValue();
                    if (loginViewState == null) {
                        return;
                    } else {
                        Resource<GetTokenDto> rToken = getTokenUseCase.invoke(loginViewState.getUsername(), loginViewState.getPassword());
                        if (rToken instanceof Resource.ResourceSuccess) {

                        } else if (rToken instanceof Resource.ResourceFailed) {
                            mViewEffects.postValue(new LoginViewEffect.AuthenticationErrorEffect(((Resource.ResourceFailed<GetTokenDto>) rToken).getUiText()));
                        }
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
