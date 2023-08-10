package vn.ping.ping_todo_api.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String NAME_PATTERN = "^[\\p{L}]+(?:[\\p{Zs}][\\p{L}]+)*$";
    private static final String PHONE_NUMBER_PATTERN = "^0[0-9]{9,10}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final Pattern PATTERN_NAME = Pattern.compile(NAME_PATTERN);
    private static final Pattern PATTERN_PHONE_NUMBER = Pattern.compile(PHONE_NUMBER_PATTERN);
    private static final Pattern PATTERN_PASSWORD = Pattern.compile(PASSWORD_PATTERN);
    public static boolean validateName(final String name) {
        Matcher matcher = PATTERN_NAME.matcher(name);
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(final String phoneNumber) {
        Matcher matcher = PATTERN_PHONE_NUMBER.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean validatePassword(final String password) {
        Matcher matcher = PATTERN_PASSWORD.matcher(password);
        return matcher.matches();
    }
}
