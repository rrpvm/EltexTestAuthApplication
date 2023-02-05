package com.rrpvm.authtesh.domain.helpers;

import android.content.Context;
import android.widget.Toast;

import com.rrpvm.authtesh.domain.entity.common.UiText;

public class ToastHelper {
    public static void showText(UiText uiText, Context context) {
        if (context == null) return;
        if (uiText instanceof UiText.UiTextDynamicString) {
            Toast.makeText(context, ((UiText.UiTextDynamicString) uiText).getMessage(), Toast.LENGTH_SHORT).show();
        } else if (uiText instanceof UiText.UiTextResourceString) {
            Toast.makeText(context, ((UiText.UiTextResourceString) uiText).getStringResource(), Toast.LENGTH_SHORT).show();
        }
    }
}
