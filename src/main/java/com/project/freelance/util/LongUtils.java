package com.project.freelance.util;

public class LongUtils {

    public static boolean isNotNullAndNotZero(Long input) {
        return input != null && input != 0;
    }

    public static boolean isNotNullAndPositive(Long input) {
        return input != null && input > 0;
    }

    public static boolean isNullOrZero(Long input) {
        return input == null || input == 0;
    }

    public static boolean isNullOrNegative(Long input) {
        return input == null || input < 0;
    }

    public static boolean isNullOrZeroOrNegative(Long input) {
        return input == null || input <= 0;
    }

    public static boolean isNotNullAndNotNegative(Long input) {
        return input != null && input >= 0;
    }

    public static boolean isNotNullAndLowerThanOne(Long input) {
        return input != null && input < 1;
    }
}
