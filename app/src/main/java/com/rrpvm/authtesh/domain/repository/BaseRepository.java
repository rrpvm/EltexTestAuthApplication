package com.rrpvm.authtesh.domain.repository;

import android.util.Log;

import com.rrpvm.authtesh.BuildConfig;
import com.rrpvm.authtesh.domain.entity.common.UiText;
import com.rrpvm.authtesh.domain.entity.network.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;
import retrofit2.Response;

public class BaseRepository {
    // protected final <T> Resource<T> wrapRequestWithStatusCode(Callable<Response<T>> request) {
    protected final <T, Rp> Resource<Rp> wrapRequestWithStatusCode(Callable<T> request, Function<T, Rp> mapper) {
        T result;
        int statusCode = -1;
        try {
            result = request.call();
        } catch (SSLHandshakeException e) {
            return handleIOServerException(e, statusCode);
        } catch (IOException e) {
            return handleIOException(e, statusCode);
        } catch (HttpException e) {
            return handleHttpException(e);
        } catch (Throwable e) {
            return handleException(e, statusCode);
        }
        return new Resource.ResourceSuccess<Rp>(mapper.apply(result), statusCode);
    }

    private Resource.ResourceFailed handleIOServerException(SSLHandshakeException e, int statusCode) {
        e.printStackTrace();
        return new Resource.ResourceFailed(UiText.ioErrorServer(), statusCode);
    }

    private Resource.ResourceFailed handleIOException(IOException e, int statusCode) {
        e.printStackTrace();
        UiText output;
        if (e.getMessage() == null) output = UiText.ioError();
        else output = new UiText.UiTextDynamicString(e.getMessage());
        return new Resource.ResourceFailed(output, statusCode);
    }

    private Resource.ResourceFailed handleHttpException(HttpException e) {
        e.printStackTrace();
        UiText output;
        if (e.message() == null) output = UiText.ioErrorServer();
        else output = new UiText.UiTextDynamicString(e.message());
        return new Resource.ResourceFailed(output, e.code());
    }

    private Resource.ResourceFailed handleException(Throwable e, int statusCode) {
        Log.e(BuildConfig.APP_TAG, e.toString() + Arrays.toString(e.getStackTrace()));
        return new Resource.ResourceFailed(UiText.unknownError(), statusCode);
    }
}
