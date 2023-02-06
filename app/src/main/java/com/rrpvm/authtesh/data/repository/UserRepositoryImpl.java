package com.rrpvm.authtesh.data.repository;

import com.rrpvm.authtesh.data.network.dto.GetUserInfoDto;
import com.rrpvm.authtesh.data.network.source.TestApi;
import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.UserInfoModel;
import com.rrpvm.authtesh.domain.repository.BaseRepository;
import com.rrpvm.authtesh.domain.repository.UserRepository;


import javax.inject.Inject;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {

    private final TestApi testApi;

    @Inject
    public UserRepositoryImpl(TestApi testApi) {
        this.testApi = testApi;
    }

    @Override
    public Resource<UserInfoModel> getUserInfo() {
        return wrapRequestWithStatusCode(
                () -> testApi.getUserInfo().get(),
                GetUserInfoDto::toUserInfo
        );
    }
}
