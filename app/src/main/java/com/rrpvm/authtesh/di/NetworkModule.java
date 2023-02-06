package com.rrpvm.authtesh.di;

import static com.rrpvm.authtesh.data.repository.AuthRepositoryImpl.TOKEN_REQUEST_KEY;
import static com.rrpvm.authtesh.domain.utils.Constants.EMPTY_STRING;

import android.util.Log;
import com.orhanobut.hawk.Hawk;
import com.rrpvm.authtesh.BuildConfig;
import com.rrpvm.authtesh.data.network.source.TestApi;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
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
    public Interceptor authenticationInterceptor() {
        return chain -> chain.proceed(
                chain.request()
                        .newBuilder()
                        .addHeader("Authorization", Hawk.get(TOKEN_REQUEST_KEY) == null ? "Bearer zxc" : "Bearer " + Hawk.get(TOKEN_REQUEST_KEY))
                        .build()
        );
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(
            HttpLoggingInterceptor httpLoggingInterceptor,
            Interceptor authenticationInterceptor
    ) {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(authenticationInterceptor)
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
                .baseUrl(BuildConfig.BASE_API_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .build();
    }

    @Provides
    @Singleton
    public TestApi provideTestApi(Retrofit retrofit) {
        return retrofit.create(TestApi.class);
    }

}
