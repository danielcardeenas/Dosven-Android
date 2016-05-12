package io.leind.dosven.Utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leind on 11/05/16.
 */
public class Utils {
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Password regex:
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
     * (?=\\S+$)         # no whitespace allowed in the entire string
     * .{4,}             # anything, at least six places though
     * $                 # end-of-string
     * */
    public static boolean isValidPassword(CharSequence target) {
        Pattern pattern; Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=\\S+$).{6,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(target);

        return matcher.matches();
    }
}
