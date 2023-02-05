package com.rrpvm.authtesh.data.network;


import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TestApi {
    @POST("oauth/token")
    Call<String> getToken(
            @Query("grant_type") String grantType,
            @Query("username") String username,
            @Query("password") String password,
            @Header("Authorization") String base64Credentials
    );
}
