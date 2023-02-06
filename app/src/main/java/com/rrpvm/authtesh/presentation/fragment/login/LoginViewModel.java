package com.rrpvm.authtesh.presentation.fragment.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rrpvm.authtesh.domain.usecase.GetCurrentTokenUseCase;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;
import com.rrpvm.authtesh.presentation.fragment.authorization.data.AuthorizationViewState;

import java.util.Optional;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final GetCurrentTokenUseCase getCurrentTokenUseCase;
    private MutableLiveData<LoginViewEffect> mViewEffects = new MutableLiveData<>(new LoginViewEffect.InitState());

    @Inject
    public LoginViewModel(GetCurrentTokenUseCase getCurrentTokenUseCase) {
        this.getCurrentTokenUseCase = getCurrentTokenUseCase;
    }

    public void onInit() {
        Optional<String> token = getCurrentTokenUseCase.invoke();
        if (token.isPresent()) {

            //do load users(check for key not expired)
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

    public LiveData<LoginViewEffect> getViewEffects() {
        return mViewEffects;
    }
}
