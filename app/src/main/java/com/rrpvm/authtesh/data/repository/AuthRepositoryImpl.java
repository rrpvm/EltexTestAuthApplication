package com.rrpvm.authtesh.data.repository;

import com.rrpvm.authtesh.R;
import com.rrpvm.authtesh.data.network.TestApi;
import com.rrpvm.authtesh.domain.entity.network.Resource;
import com.rrpvm.authtesh.domain.repository.AuthRepository;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;

@Singleton
public class AuthRepositoryImpl implements AuthRepository {
    private final TestApi testApi;

    @Inject
    public AuthRepositoryImpl(TestApi testApi) {
        this.testApi = testApi;
    }

    @Override
    public Resource<String> getToken(String username, char[] password) {
        String pass = Arrays.toString(password);
        String constant = "Basic ImFuZHJvaWQtY2xpZW50OnBhc3N3b3JkIg==";
        try {
            Call<String> call = testApi.getToken("password", username, pass, constant);
            Response<String> response = call.execute();
            pass = null;
            return new Resource.ResourceSuccess(response.body(), response.code());
        } catch (IOException e) {
            pass = null;
            return new Resource.ResourceFailed(R.string.app_name);
        }
    }
}
