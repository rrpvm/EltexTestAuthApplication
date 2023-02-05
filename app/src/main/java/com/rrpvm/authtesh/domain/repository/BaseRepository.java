package com.rrpvm.authtesh.domain.repository;

import android.util.Log;

import com.rrpvm.authtesh.BuildConfig;
import com.rrpvm.authtesh.domain.entity.common.UiText;
import com.rrpvm.authtesh.domain.entity.network.Resource;

import java.io.IOException;
import java.util.Arrays;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;

public class BaseRepository {
    protected final <T> Resource<T> wrapRequest(Call<T> request) {
        T result = null;
        try {
            Response<T> response = request.execute();
            result = response.body();
        } catch (SSLHandshakeException e) {
            return handleIOServerException(e);
        } catch (IOException e) {
            return handleIOException(e);
        } catch (HttpException e) {
            return handleHttpException(e);
        } catch (Exception e) {
            return handleException(e);
        }
        return new Resource.ResourceSuccess<T>(result);
    }

    private Resource.ResourceFailed handleIOServerException(SSLHandshakeException e) {
        e.printStackTrace();
        return new Resource.ResourceFailed(UiText.ioErrorServer());
    }

    private Resource.ResourceFailed handleIOException(IOException e) {
        e.printStackTrace();
        return new Resource.ResourceFailed(UiText.ioError());
    }

    private Resource.ResourceFailed handleHttpException(HttpException e) {
        e.printStackTrace();
        return new Resource.ResourceFailed(UiText.ioErrorServer());
    }

    private Resource.ResourceFailed handleException(Exception e) {
        Log.e(BuildConfig.APP_TAG, e.toString() + Arrays.toString(e.getStackTrace()));
        return new Resource.ResourceFailed(UiText.unknownError());
    }
}
