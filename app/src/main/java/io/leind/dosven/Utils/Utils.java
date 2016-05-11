package io.leind.dosven.Utils;

import android.text.TextUtils;

/**
 * Created by leind on 11/05/16.
 */
public class Utils {
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
