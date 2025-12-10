package ru.teamscore.randomizer;

import java.util.regex.Pattern;

public class RussianCarNumber {
    private final String series;
    private final String number;
    private final String region;

    private RussianCarNumber(String series, String number, String region) {
        this.series = series;
        this.number = number;
        this.region = region;
    }

    public static RussianCarNumber valueOf(String series, String number, String region) {
        RussianCarValidator.validateSeries(series);
        RussianCarValidator.validateNumber(number);
        RussianCarValidator.validateRegion(region);

        return new RussianCarNumber(series, number, region);
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getRegion() {
        return region;
    }

    static class RussianCarValidator {
        private static final Pattern seriesPattern = Pattern.compile("^[АВЕКМНОРСТУХ]{3}$");
        private static final Pattern numberPattern = Pattern.compile("^\\d{2}[1-9]$");
        private static final Pattern regionPattern =
            Pattern.compile("^[1-9]$|^[1-9]\\d$|^[1-9]\\d{2}$");

        private RussianCarValidator() {}

        static void validateSeries(String series) {
            if (series.length() != 3) {
                throw new IllegalArgumentException("Series length must be equal 3");
            }

            if (!seriesPattern.matcher(series).find()) {
                throw new IllegalArgumentException("Series must include only chars: АВЕКМНОРСТУХ");
            }
        }

        static void validateNumber(String number) {
            if (number.length() != 3) {
                throw new IllegalArgumentException("Number length must be equal 3");
            }

            if (!numberPattern.matcher(number).find()) {
                throw new IllegalArgumentException("Number must include only next interval: " +
                    "001-999");
            }
        }

        static void validateRegion(String region) {
            if (!regionPattern.matcher(region).find()) {
                throw new IllegalArgumentException("Region number must include only in next " +
                    "variants: (1-9), (1-9)(0-9), (1-9)(0-9)(0-9)");
            }
        }
    }
}
