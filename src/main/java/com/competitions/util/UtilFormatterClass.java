package com.competitions.util;

public class UtilFormatterClass {

    public static String convertToYyyyMmDd(int year, int month, int day) throws IllegalArgumentException {
        StringBuilder result = new StringBuilder();
        result.append(year).append("-");
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("Неправильный номер месяца");
        }
        if (month < 10) {
            result.append("0");
        }
        result.append(month).append("-");
        if (day <= 0 || day > 31) {
            throw new IllegalArgumentException("Неправильный номер дня");
        }
        if (day < 10) {
            result.append("0");
        }
        result.append(day);
        return result.toString();
    }
}
