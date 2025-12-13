package ru.teamscore.supplier.inn;

import java.util.regex.Pattern;

class InnValidator {
    private static final Pattern regions = Pattern.compile("^([0-7][1-9]|10|20|30|40|50|60|70|80" +
        "|83|86|87" +
        "|89|91|92|99" +
        "])$");
    private static final Pattern department = Pattern.compile("^\\d[1-9]$");
    private static final Pattern recordId = Pattern.compile("^\\d{4,5}[1-9]$");

    private InnValidator() {
    }

    static void validateInn(String validationInn) {
        if (validationInn.length() == 10) {
            validateOrganizationInn(validationInn);
        } else if (validationInn.length() == 12) {
            validatePhysicsInn(validationInn);
        } else {
            throw new IllegalArgumentException("INN length must be 10 or 12");
        }
    }

    static void validateOrganizationInn(String inn) {
        if (!regions.matcher(inn.substring(0, 2)).find()) {
            throw new IllegalArgumentException("1-2 symbols must be code region");
        }
        if (!department.matcher(inn.substring(2, 4)).find()) {
            throw new IllegalArgumentException("3-4 symbols must be digits");
        }
        if (!recordId.matcher(inn.substring(4, 10)).find()) {
            throw new IllegalArgumentException("4-9 symbols must be digits");
        }
        validateOrganizationControlDigit(inn);
    }

    static void validateOrganizationControlDigit(String inn) {
        int sum = 0;
        int[] coefficients = new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8};

        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Character.getNumericValue(inn.charAt(i));
        }

        if ((sum % 11) % 10 != Character.getNumericValue(inn.charAt(9))) {
            throw new IllegalArgumentException("Control digit must be correct");
        }
    }

    static void validatePhysicsInn(String inn) {
        if (!regions.matcher(inn.substring(0, 2)).find()) {
            throw new IllegalArgumentException("1-2 symbols must be code region");
        }
        if (!department.matcher(inn.substring(2, 4)).find()) {
            throw new IllegalArgumentException("3-4 symbols must be digits");
        }
        if (!recordId.matcher(inn.substring(4, 10)).find()) {
            throw new IllegalArgumentException("4-10 symbols must be digits");
        }
        validatePhysicsControlDigits(inn);
    }

    static void validatePhysicsControlDigits(String inn) {
        int firstControlSum = 0;
        int secondControlSum = 0;
        int[][] coefficients =
            new int[][]{{7, 2, 4, 10, 3, 5, 9, 4, 6, 8}, {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8}};

        for (int i = 0; i < coefficients[0].length; i++) {
            firstControlSum += coefficients[0][i] * Character.getNumericValue(inn.charAt(i));
        }
        if ((firstControlSum % 11) % 10 != Character.getNumericValue(inn.charAt(10))) {
            throw new IllegalArgumentException("first control digit must be correct");
        }

        for (int i = 0; i < coefficients[1].length; i++) {
            secondControlSum += coefficients[1][i] * Character.getNumericValue(inn.charAt(i));
        }
        if ((secondControlSum % 11) % 10 != Character.getNumericValue(inn.charAt(11))) {
            throw new IllegalArgumentException("second control digit must be correct");
        }
    }
}
