package com.rrpvm.authtesh.data.network;


import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TestApi {

    @POST("oauth/token")
    void getToken(
            @Query("grant_type") String grantType,
            @Query("username") String username,
            @Query("password") String password,
            @Query("Authorization") String base64Credentials
    );
}
