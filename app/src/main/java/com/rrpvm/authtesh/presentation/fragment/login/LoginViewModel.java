package com.rrpvm.authtesh.presentation.fragment.login;

import static com.rrpvm.authtesh.domain.utils.Constants.EMPTY_STRING;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.UserInfoModel;
import com.rrpvm.authtesh.domain.usecase.GetCurrentTokenUseCase;
import com.rrpvm.authtesh.domain.usecase.GetUserInfoUseCase;
import com.rrpvm.authtesh.domain.usecase.SetCurrentTokenUseCase;
import com.rrpvm.authtesh.presentation.fragment.login.data.LoginViewEffect;

import java.util.Optional;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final GetCurrentTokenUseCase getCurrentTokenUseCase;
    private final SetCurrentTokenUseCase setCurrentTokenUseCase;
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final MutableLiveData<LoginViewEffect> mViewEffects = new MutableLiveData<>(new LoginViewEffect.InitState());

    @Inject
    public LoginViewModel(GetCurrentTokenUseCase getCurrentTokenUseCase, SetCurrentTokenUseCase setCurrentTokenUseCase, GetUserInfoUseCase getUserInfoUseCase) {
        this.getCurrentTokenUseCase = getCurrentTokenUseCase;
        this.setCurrentTokenUseCase = setCurrentTokenUseCase;
        this.getUserInfoUseCase = getUserInfoUseCase;
    }

    public void onInit() {
        Optional<String> token = getCurrentTokenUseCase.invoke();
        if (token.isPresent() && !token.get().isEmpty()) {
            Disposable d = Single.just((getUserInfoUseCase.invoke())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userInfo -> {
                if (userInfo instanceof Resource.ResourceFailed) {
                    if (userInfo.getHttpStatusCode() == 401) {//сброс токена
                        setCurrentTokenUseCase.invoke(EMPTY_STRING);
                    }
                    mViewEffects.setValue(new LoginViewEffect.AuthenticationErrorEffect());
                } else if (userInfo instanceof Resource.ResourceSuccess) {
                    mViewEffects.setValue(new LoginViewEffect.AuthenticationSuccess(((Resource.ResourceSuccess<UserInfoModel>) userInfo).getData()));
                }
            });
        } else {
            mViewEffects.setValue(new LoginViewEffect.AuthenticationErrorEffect());
        }
    }

    public LiveData<LoginViewEffect> getViewEffects() {
        return mViewEffects;
    }
}
