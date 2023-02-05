package com.rrpvm.authtesh.presentation.fragment.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.orhanobut.hawk.Hawk;
import com.rrpvm.authtesh.data.repository.AuthRepositoryImpl;
import com.rrpvm.authtesh.domain.repository.AuthRepository;
import com.rrpvm.authtesh.domain.usecase.GetTokenUseCase;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewState;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginViewState> mViewState = new MutableLiveData<>(new LoginViewState());
    private MutableLiveData<LoginViewEffect> mViewEffects = new MutableLiveData<>(new LoginViewEffect.InitState());

    @Inject
    public LoginViewModel(SavedStateHandle savedStateHandle, GetTokenUseCase getTokenUseCase) {

    }

    public void onInit() {
        String token = Hawk.get("1337");
        if (token != null) {

        } else {

        }
        new Thread(() -> {
            try {
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
