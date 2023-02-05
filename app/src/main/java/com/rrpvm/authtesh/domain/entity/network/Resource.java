package com.rrpvm.authtesh.domain.entity.network;

public abstract class Resource<T> {
    public static class ResourceSuccess<T> extends Resource<T> {
        private T mData;
        private int mCode;

        public T getData() {
            return mData;
        }

        public int getCode() {
            return mCode;
        }

        public ResourceSuccess(T data, int code) {
            mData = data;
            mCode = code;
        }
    }

    public static class ResourceFailed<T> extends Resource<T> {
        private int mStringResource;

        public int getStringResource() {
            return mStringResource;
        }

        public ResourceFailed(int mStringResource) {
            this.mStringResource = mStringResource;
        }

    }
}
