package com.rrpvm.authtesh.domain.entity.network;

import com.rrpvm.authtesh.domain.entity.common.UiText;

public interface Resource<T> {
    class ResourceSuccess<T> implements Resource<T> {
        private final T mData;
        private final int statusCode;

        public T getData() {
            return mData;
        }

        public ResourceSuccess(T data, int statusCode) {
            mData = data;
            this.statusCode = statusCode;
        }

        @Override
        public int getHttpStatusCode() {
            return 0;
        }
    }

    class ResourceFailed<T> implements Resource<T> {
        private final UiText mUiText;
        private final int statusCode;
        public UiText getUiText() {
            return mUiText;
        }

        public ResourceFailed(UiText mUiText, int statusCode) {
            this.mUiText = mUiText;
            this.statusCode = statusCode;
        }

        @Override
        public int getHttpStatusCode() {
            return 0;
        }
    }

    int getHttpStatusCode();
}
