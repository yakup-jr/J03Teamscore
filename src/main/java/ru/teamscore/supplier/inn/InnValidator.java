package ru.teamscore.supplier.inn;

import java.util.regex.Pattern;

class InnValidator {
    private static final Pattern REGIONS = Pattern.compile("^(0[1-9]|[1-7]\\d|8[0367]|9[129])$");
    private static final Pattern DEPARTMENT = Pattern.compile("^\\d[1-9]$");
    private static final Pattern RECORD_ORGANIZATION = Pattern.compile("^\\d{5}$");
    private static final Pattern RECORD_PHYSICS = Pattern.compile("^\\d{6}$");

    private InnValidator() {
    }

    static void validateInn(String inn) {
        if (inn.length() == 10) {
            validateFormat(inn, RECORD_ORGANIZATION, 9);
            validateControlDigit(inn, new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8}, 9);
        } else if (inn.length() == 12) {
            validateFormat(inn, RECORD_PHYSICS, 10);
            validateControlDigit(inn, new int[]{7, 2, 4, 10, 3, 5, 9, 4, 6, 8}, 10);
            validateControlDigit(inn, new int[]{3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8}, 11);
        } else {
            throw new IllegalArgumentException("INN length must be 10 or 12");
        }
    }

    private static void validateFormat(String inn, Pattern recordIdPattern, int endDigit) {
        if (!REGIONS.matcher(inn.substring(0, 2)).find()) {
            throw new IllegalArgumentException("1-2 symbols must be code region");
        }
        if (!DEPARTMENT.matcher(inn.substring(2, 4)).find()) {
            throw new IllegalArgumentException("3-4 symbols must be digits");
        }
        if (!recordIdPattern.matcher(inn.substring(4, endDigit)).find()) {
            throw new IllegalArgumentException("5-%d symbols must be digits".formatted(endDigit));
        }
    }

    private static void validateControlDigit(String inn, int[] coefficients, int index) {
        int sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Character.getNumericValue(inn.charAt(i));
        }
        if ((sum % 11) % 10 != Character.getNumericValue(inn.charAt(index))) {
            throw new IllegalArgumentException("Control digit must be valid");
        }
    }
}
