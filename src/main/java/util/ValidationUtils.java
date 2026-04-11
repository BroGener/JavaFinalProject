package util;

public final class ValidationUtils {
    private ValidationUtils() {}

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
