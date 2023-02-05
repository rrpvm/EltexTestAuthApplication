package com.rrpvm.authtesh.domain.entity.network;

import com.rrpvm.authtesh.domain.entity.common.UiText;

public interface Resource<T> {
    class ResourceSuccess<T> implements Resource<T> {
        private final T mData;

        public T getData() {
            return mData;
        }

        public ResourceSuccess(T data) {
            mData = data;
        }
    }

    class ResourceFailed<T> implements Resource<T> {
        private UiText mUiText;

        public UiText getUiText() {
            return mUiText;
        }

        public ResourceFailed(UiText mUiText) {
            this.mUiText = mUiText;
        }

    }
}
