package com.rrpvm.authtesh.data.repository;

import com.orhanobut.hawk.Hawk;
import com.rrpvm.authtesh.BuildConfig;
import com.rrpvm.authtesh.data.network.dto.GetTokenDto;
import com.rrpvm.authtesh.data.network.source.TestApi;
import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.model.TokenModel;
import com.rrpvm.authtesh.domain.repository.AuthRepository;
import com.rrpvm.authtesh.domain.repository.BaseRepository;


import java.util.Optional;
import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthRepositoryImpl extends BaseRepository implements AuthRepository {
    private final TestApi testApi;

    @Inject
    public AuthRepositoryImpl(TestApi testApi) {
        this.testApi = testApi;
    }

    @Override
    public Resource<TokenModel> getToken(String username, String password) {
        return wrapRequestWithStatusCode(
                () -> testApi.getToken("password", username, password, getAuthorizationHeader()).get(),
                GetTokenDto::toTokenModel
        );
    }

    @Override
    public Optional<String> getCurrentToken() {
        String token = Hawk.get(TOKEN_REQUEST_KEY);
        if (token != null) return Optional.of(token);
        return Optional.empty();
    }

    @Override
    public void setCurrentToken(String token) {
        Hawk.put(TOKEN_REQUEST_KEY, token);
    }

    //на будущее - можно будет переделать под не констатнту, где мы сами через Base64 encoder создаем хедер, либо любой другой способ
    private String getAuthorizationHeader() {
        return "Basic YW5kcm9pZC1jbGllbnQ6cGFzc3dvcmQ=";
    }

    public static final String TOKEN_REQUEST_KEY = String.format("%s:access_token", BuildConfig.APP_TAG);
}
