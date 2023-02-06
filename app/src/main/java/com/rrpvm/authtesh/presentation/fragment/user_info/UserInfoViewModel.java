package com.rrpvm.authtesh.presentation.fragment.user_info;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.UserInfoModel;
import com.rrpvm.authtesh.domain.usecase.GetUserInfoUseCase;
import com.rrpvm.authtesh.presentation.fragment.user_info.data.UserInfoViewEffect;
import com.rrpvm.authtesh.presentation.fragment.user_info.data.UserInfoViewState;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class UserInfoViewModel extends ViewModel {
    private final GetUserInfoUseCase getUserInfoUseCase;
    private final SavedStateHandle savedStateHandle;
    @Nullable
    private Disposable fetchJob = null;

    @Inject
    public UserInfoViewModel(GetUserInfoUseCase getUserInfoUseCase, SavedStateHandle savedStateHandle) {
        this.getUserInfoUseCase = getUserInfoUseCase;
        this.savedStateHandle = savedStateHandle;
    }

    private MutableLiveData<UserInfoViewState> viewState = new MutableLiveData<>(new UserInfoViewState(null));
    private MutableLiveData<UserInfoViewEffect> viewEffect = new MutableLiveData<>(new UserInfoViewEffect.InitViewEffect());

    public LiveData<UserInfoViewState> getViewState() {
        return viewState;
    }

    public LiveData<UserInfoViewEffect> getViewEffect() {
        return viewEffect;
    }

    public void onInitialize() {
        UserInfoModel userInfoModel = savedStateHandle.get("prefetched_data");
        if (userInfoModel != null) {
            if (viewState.getValue() == null || viewState.getValue().userInfo == null) {
                viewState.postValue(new UserInfoViewState(userInfoModel));
            }
        } else {
            fetchUserInfo();
        }
    }

    public void onHouseClick(String houseId) {
        viewEffect.setValue(new UserInfoViewEffect.GoHouseScreenViewEffect(houseId));
    }
    public void clearEffects(){
        viewEffect.setValue(new UserInfoViewEffect.InitViewEffect());
    }

    private void fetchUserInfo() {
        if (fetchJob != null && !fetchJob.isDisposed()) {
            fetchJob.dispose();
            fetchJob = null;
        }
        fetchJob = Single.fromCallable(() -> {
            return getUserInfoUseCase.invoke();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userInfo -> {
            if (userInfo instanceof Resource.ResourceFailed) {
                viewEffect.setValue(new UserInfoViewEffect.ShowText(((Resource.ResourceFailed<UserInfoModel>) userInfo).getUiText()));
                //  mViewEffects.setValue(new AuthorizationViewEffect.ShowText(((Resource.ResourceFailed<TokenModel>) getTokenDtoResource).getUiText()));
            } else if (userInfo instanceof Resource.ResourceSuccess) {
                viewState.setValue(new UserInfoViewState(((Resource.ResourceSuccess<UserInfoModel>) userInfo).getData()));
            }
        });
    }
}
