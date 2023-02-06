package com.rrpvm.authtesh.domain.helpers;

import static com.rrpvm.authtesh.domain.utils.Constants.EMPTY_STRING;

import android.widget.EditText;

import javax.annotation.Nullable;

public class TextViewsHelper {
    public static void setTextValueOrEmpty(@Nullable EditText editText, String value) {
        if (editText == null) return;
        editText.setText(value == null ? EMPTY_STRING : value);
    }
}
