package ru.teamscore.randomizer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RussianCarValidatorTest {

    @ParameterizedTest
    @MethodSource("provideInvalidSeries")
    void validateSeries_whenInvalid_throwIllegalArgumentException(String series, String exceptionMessage) {
        assertThrows(IllegalArgumentException.class, 
            () -> RussianCarNumber.RussianCarValidator.validateSeries(series), exceptionMessage);
    }

    private static Stream<Arguments> provideInvalidSeries() {
        return Stream.of(
            Arguments.of("АВ", "Series length must be equal 3"),
            Arguments.of("АВЕК", "Series length must be equal 3"),
            Arguments.of("АБВ", "Series must include only chars: АВЕКМНОРСТУХ"),
            Arguments.of("ABC", "Series must include only chars: АВЕКМНОРСТУХ")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidSeries")
    void validateSeries_whenValid_doesNotThrow(String series) {
        assertDoesNotThrow(() -> RussianCarNumber.RussianCarValidator.validateSeries(series));
    }

    private static Stream<Arguments> provideValidSeries() {
        return Stream.of(
            Arguments.of("АВЕ"),
            Arguments.of("КМН"),
            Arguments.of("ОРС")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumber")
    void validateNumber_whenInvalid_throwIllegalArgumentException(String number, String exceptionMessage) {
        assertThrows(IllegalArgumentException.class, 
            () -> RussianCarNumber.RussianCarValidator.validateNumber(number), exceptionMessage);
    }

    private static Stream<Arguments> provideInvalidNumber() {
        return Stream.of(
            Arguments.of("12", "Number length must be equal 3"),
            Arguments.of("1234", "Number length must be equal 3"),
            Arguments.of("000", "Number must include only next interval: 001-999"),
            Arguments.of("100", "Number must include only next interval: 001-999")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidNumber")
    void validateNumber_whenValid_doesNotThrow(String number) {
        assertDoesNotThrow(() -> RussianCarNumber.RussianCarValidator.validateNumber(number));
    }

    private static Stream<Arguments> provideValidNumber() {
        return Stream.of(
            Arguments.of("001"),
            Arguments.of("123"),
            Arguments.of("999")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidRegion")
    void validateRegion_whenInvalid_throwIllegalArgumentException(String region, String exceptionMessage) {
        assertThrows(IllegalArgumentException.class, 
            () -> RussianCarNumber.RussianCarValidator.validateRegion(region), exceptionMessage);
    }

    private static Stream<Arguments> provideInvalidRegion() {
        return Stream.of(
            Arguments.of("0", "Region number must include only in next variants: (1-9), (1-9)(0-9), (1-9)(0-9)(0-9)"),
            Arguments.of("00", "Region number must include only in next variants: (1-9), (1-9)(0-9), (1-9)(0-9)(0-9)"),
            Arguments.of("1000", "Region number must include only in next variants: (1-9), (1-9)(0-9), (1-9)(0-9)(0-9)")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidRegion")
    void validateRegion_whenValid_doesNotThrow(String region) {
        assertDoesNotThrow(() -> RussianCarNumber.RussianCarValidator.validateRegion(region));
    }

    private static Stream<Arguments> provideValidRegion() {
        return Stream.of(
            Arguments.of("1"),
            Arguments.of("77"),
            Arguments.of("999")
        );
    }
}
