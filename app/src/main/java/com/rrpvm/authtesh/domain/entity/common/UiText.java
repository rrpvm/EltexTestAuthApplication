package com.rrpvm.authtesh.domain.entity.common;

import com.rrpvm.authtesh.R;

public class UiText {
    private final int mStringResource;

    public int getmStringResource() {
        return mStringResource;
    }

    public UiText(int mStringResource) {
        this.mStringResource = mStringResource;
    }

    public static UiText ioErrorServer() {
        return new UiText(R.string.app_name);
    }

    public static UiText unknownError() {
        return new UiText(R.string.app_name);
    }

    public static UiText ioError() {
        return new UiText(R.string.app_name);
    }
}
