package ru.teamscore.randomizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RussianCarNumberTest {

    @ParameterizedTest
    @MethodSource("provideValueOf")
    void valueOf_whenInvalid_throwIllegalArgumentException(String series, String number,
                                                           String region, String exceptionMessage) {
        assertThrows(IllegalArgumentException.class, () -> RussianCarNumber.valueOf(series, number, region),
            exceptionMessage);
    }

    private static Stream<Arguments> provideValueOf() {
        return Stream.of(
            Arguments.of("АВ", "123", "77", "Series length must be equal 3"),
            Arguments.of("АБВ", "123", "77", "Series must include only chars: АВЕКМНОРСТУХ"),
            Arguments.of("АВЕ", "12", "77", "Number length must be equal 3"),
            Arguments.of("АВЕ", "000", "77", "Number must include only next interval: 001-999"),
            Arguments.of("АВЕ", "123", "0", "Region number must include only in next variants: (1-9), (1-9)(0-9), (1-9)(0-9)(0-9)")
        );
    }

    @Test
    void valueOf_whenValid_returnRussianCarNumber() {
        assertDoesNotThrow(() -> RussianCarNumber.valueOf("АВЕ", "123", "77"));
    }
}
