package com.rrpvm.authtesh.domain.repository;

import android.util.Log;

import com.rrpvm.authtesh.BuildConfig;
import com.rrpvm.authtesh.domain.entity.common.UiText;
import com.rrpvm.authtesh.domain.entity.network.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

public class BaseRepository {
    protected final <T> Resource<T> wrapRequest(Callable<T> request) {
        T result;
        try {
            try {
                result = request.call();
            } catch (ExecutionException ep) {
                throw ep.getCause();
            }
        } catch (SSLHandshakeException e) {
            return handleIOServerException(e);
        } catch (IOException e) {
            return handleIOException(e);
        } catch (HttpException e) {
            return handleHttpException(e);
        } catch (Throwable e) {
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
        UiText output;
        if (e.getMessage() == null) output = UiText.ioError();
        else output = new UiText.UiTextDynamicString(e.getMessage());
        return new Resource.ResourceFailed(output);
    }

    private Resource.ResourceFailed handleHttpException(HttpException e) {
        e.printStackTrace();
        UiText output;
        if (e.message() == null) output = UiText.ioErrorServer();
        else output = new UiText.UiTextDynamicString(e.message());
        return new Resource.ResourceFailed(output);
    }

    private Resource.ResourceFailed handleException(Throwable e) {
        Log.e(BuildConfig.APP_TAG, e.toString() + Arrays.toString(e.getStackTrace()));
        return new Resource.ResourceFailed(UiText.unknownError());
    }
}
