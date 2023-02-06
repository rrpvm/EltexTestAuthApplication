package com.rrpvm.authtesh.domain.usecase;

import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.UserInfoModel;
import com.rrpvm.authtesh.domain.repository.UserRepository;

import javax.inject.Inject;

public class GetUserInfoUseCase {
    private final UserRepository repository;

    @Inject
    public GetUserInfoUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public Resource<UserInfoModel> invoke() {
        return repository.getUserInfo();
    }
}
