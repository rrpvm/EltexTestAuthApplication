package com.rrpvm.authtesh.data.network.source;


import com.rrpvm.authtesh.data.network.dto.GetHouseInfoDto;
import com.rrpvm.authtesh.data.network.dto.GetTokenDto;
import com.rrpvm.authtesh.data.network.dto.GetUserInfoDto;

import java.util.concurrent.CompletableFuture;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

//можно рассуждать, почему бы не сделать интерцептор с хедром на авторизацию,но коль запросов немного, а хедеров 2,то сделаю вручную
public interface TestApi {
    @POST("oauth/token")
    CompletableFuture<GetTokenDto> getToken(
            @Query("grant_type") String grantType,
            @Query("username") String username,
            @Query("password") String password,
            @Header("Authorization") String base64Credentials
    );

    @GET("user")
    CompletableFuture<GetUserInfoDto> getUserInfo();

    @GET("houses/{id}")
    CompletableFuture<GetHouseInfoDto> getHouse(
            @Path("id") String id
    );

}
