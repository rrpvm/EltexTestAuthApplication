package com.rrpvm.authtesh.di;

import android.util.Log;

import com.rrpvm.authtesh.BuildConfig;
import com.rrpvm.authtesh.data.network.source.TestApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(BuildConfig.APP_TAG, message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(
            HttpLoggingInterceptor httpLoggingInterceptor
    ) {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .cache(null)
                .build();
    }

    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    @Singleton
    public TestApi provideTestApi(Retrofit retrofit) {
        return retrofit.create(TestApi.class);
    }

    private final static String BASE_URL_API = "http://smart.eltex-co.ru:8271/api/v1/";
}
