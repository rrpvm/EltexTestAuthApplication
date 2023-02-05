package com.rrpvm.authtesh.domain.entity.common;

import androidx.annotation.StringRes;

import com.rrpvm.authtesh.R;

public abstract class UiText {

    public static class UiTextDynamicString extends UiText {
        private final String mMessage;

        public UiTextDynamicString(String mMessage) {
            this.mMessage = mMessage;
        }

        public String getMessage() {
            return mMessage;
        }
    }

    public static class UiTextResourceString extends UiText {
        private final int mStringResource;

        public int getStringResource() {
            return mStringResource;
        }

        public UiTextResourceString(@StringRes int mStringResource) {
            this.mStringResource = mStringResource;
        }
    }


    public static UiText ioErrorServer() {
        return new UiTextResourceString(R.string.io_server_error);
    }

    public static UiText unknownError() {
        return new UiTextResourceString(R.string.unknown_error);
    }

    public static UiText ioError() {
        return new UiTextResourceString(R.string.io_error);
    }
}
