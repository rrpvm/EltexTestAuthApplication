package com.rrpvm.authtesh.presentation.fragment.house;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.HouseModel;
import com.rrpvm.authtesh.domain.model.UserInfoModel;
import com.rrpvm.authtesh.domain.usecase.GetHouseInfoUseCase;
import com.rrpvm.authtesh.presentation.fragment.house.data.HouseFragmentViewEffect;
import com.rrpvm.authtesh.presentation.fragment.user_info.data.UserInfoViewEffect;
import com.rrpvm.authtesh.presentation.fragment.user_info.data.UserInfoViewState;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HouseViewModel extends ViewModel {
    private final SavedStateHandle savedStateHandle;
    private final GetHouseInfoUseCase getHouseInfoUseCase;

    private final MutableLiveData<HouseFragmentViewEffect> viewEffect = new MutableLiveData<>(new HouseFragmentViewEffect.InitViewEffect());
    private final MutableLiveData<HouseModel> viewState = new MutableLiveData<>(null);

    public LiveData<HouseFragmentViewEffect> getViewEffects() {
        return viewEffect;
    }

    public LiveData<HouseModel> getViewState() {
        return viewState;
    }

    private Disposable fetchJob = null;

    @Inject
    public HouseViewModel(SavedStateHandle savedStateHandle, GetHouseInfoUseCase getHouseInfoUseCase) {
        this.savedStateHandle = savedStateHandle;
        this.getHouseInfoUseCase = getHouseInfoUseCase;
    }

    public void onInitialize() {
        if (viewState.getValue() != null) return;
        String id = savedStateHandle.get("houseId");
        if (id != null) {
            fetchHouseInfo(id);
        } else {
            viewEffect.setValue(new HouseFragmentViewEffect.BackToTheScreen());
        }
    }
    public void clearEffects(){
        viewEffect.setValue(new HouseFragmentViewEffect.InitViewEffect());
    }
    private void fetchHouseInfo(String id) {
        fetchJob = Single.just(getHouseInfoUseCase.invoke(id)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(houseInfo -> {
            if (houseInfo instanceof Resource.ResourceFailed) {
                viewEffect.setValue(new HouseFragmentViewEffect.ShowText(((Resource.ResourceFailed<HouseModel>) houseInfo).getUiText()));
            } else if (houseInfo instanceof Resource.ResourceSuccess) {
                viewState.setValue(((Resource.ResourceSuccess<HouseModel>) houseInfo).getData());
                // viewState.setValue(new UserInfoViewState(((Resource.ResourceSuccess<UserInfoModel>) userInfo).getData()));
            }
        });
    }
}
