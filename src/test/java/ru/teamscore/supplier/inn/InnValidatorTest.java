package ru.teamscore.supplier.inn;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InnValidatorTest {

    @ParameterizedTest
    @MethodSource("provideValidateInn")
    void validateInn_whenInvalid_throwIllegalArgumentException(String inn,
                                                               String exceptionMessage) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> InnValidator.validateInn(inn));
        assertEquals(exceptionMessage, exception.getMessage());
    }

    private static Stream<Arguments> provideValidateInn() {
        return Stream.of(
            Arguments.of("123", "INN length must be 10 or 12"),
            Arguments.of("12345678901234", "INN length must be 10 or 12"),
            Arguments.of("1234567890", "Control digit must be valid"),
            Arguments.of("123456789012", "Control digit must be valid")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidInn")
    void validateInn_whenValid_doesNotThrow(String inn) {
        assertDoesNotThrow(() -> InnValidator.validateInn(inn));
    }

    private static Stream<Arguments> provideValidInn() {
        return Stream.of(
            Arguments.of("1111111117"),
            Arguments.of("111111111130")
        );
    }


}