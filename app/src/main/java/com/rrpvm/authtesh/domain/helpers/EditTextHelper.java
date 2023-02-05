package com.rrpvm.authtesh.domain.helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.function.Function;

public class EditTextHelper {
    public static void onTextChanged(EditText editText, Function<String, Void> callback) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                callback.apply(s.toString());
            }
        });
    }
}
