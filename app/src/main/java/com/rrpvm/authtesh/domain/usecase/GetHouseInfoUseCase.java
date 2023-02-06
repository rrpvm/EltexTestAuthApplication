package com.rrpvm.authtesh.domain.usecase;

import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.HouseModel;
import com.rrpvm.authtesh.domain.repository.UserRepository;

import javax.inject.Inject;

public class GetHouseInfoUseCase {
    private final UserRepository repository;

    @Inject
    public GetHouseInfoUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public Resource<HouseModel> invoke(String id) {
        return repository.getHouseInfo(id);
    }
}
