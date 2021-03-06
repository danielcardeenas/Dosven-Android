package io.leind.dosven.Utils.METValidators;

import android.support.annotation.NonNull;

import com.rengwuxian.materialedittext.validation.METValidator;

import io.leind.dosven.Utils.Utils;

/**
 * Created by leind on 11/05/16.
 */
public class PasswordValidator extends METValidator {
    public PasswordValidator(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return Utils.isValidPassword(text);
    }
}
