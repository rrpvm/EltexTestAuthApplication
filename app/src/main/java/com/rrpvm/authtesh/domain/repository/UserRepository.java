package com.rrpvm.authtesh.domain.repository;

import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.UserInfoModel;

import javax.inject.Singleton;

public interface UserRepository {
    Resource<UserInfoModel> getUserInfo();
}
