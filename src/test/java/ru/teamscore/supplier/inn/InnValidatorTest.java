package ru.teamscore.supplier.inn;

import org.junit.jupiter.api.Test;
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
            Arguments.of("1234567890", "4-9 symbols must be digits"),
            Arguments.of("123456789012", "4-10 symbols must be digits")
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

    @ParameterizedTest
    @MethodSource("provideValidateOrganizationInn")
    void validateOrganizationInn(String inn, String exceptionMessage) {
        IllegalArgumentException illegalArgumentException =
            assertThrows(IllegalArgumentException.class,
                () -> InnValidator.validateOrganizationInn(inn));

        assertEquals(illegalArgumentException.getMessage(), exceptionMessage);
    }

    private static Stream<Arguments> provideValidateOrganizationInn() {
        return Stream.of(
            Arguments.of("9011111111", "1-2 symbols must be code region"),
            Arguments.of("0011111111", "1-2 symbols must be code region"),
            Arguments.of("11j2111111", "3-4 symbols must be digits"),
            Arguments.of("111111p111", "4-9 symbols must be digits")
        );
    }

    @Test
    void validateOrganizationControlDigit_whenInvalid_throwIllegalArgumentException() {
        String inn = "1111111111";
        assertThrows(IllegalArgumentException.class,
            () -> InnValidator.validateOrganizationControlDigit(inn));
    }

    @Test
    void validateOrganizationControlDigit_whenValid() {
        String inn = "1111111117";
        assertDoesNotThrow(() -> InnValidator.validateOrganizationControlDigit(inn));
    }

    @ParameterizedTest
    @MethodSource("provideValidatePhysicsInn")
    void validatePhysicsInn(String inn, String exceptionMessage) {
        IllegalArgumentException illegalArgumentException =
            assertThrows(IllegalArgumentException.class,
                () -> InnValidator.validatePhysicsInn(inn));

        assertEquals(illegalArgumentException.getMessage(), exceptionMessage);
    }

    private static Stream<Arguments> provideValidatePhysicsInn() {
        return Stream.of(
            Arguments.of("901111111111", "1-2 symbols must be code region"),
            Arguments.of("001111111111", "1-2 symbols must be code region"),
            Arguments.of("11j211111111", "3-4 symbols must be digits"),
            Arguments.of("1111111p1111", "4-10 symbols must be digits")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidatePhysicsControlDigits")
    void validatePhysicsControlDigits_whenInvalid(
        String inn, String exceptionMessage) {
        assertThrows(IllegalArgumentException.class,
            () -> InnValidator.validateOrganizationControlDigit(inn), exceptionMessage);
    }

    private static Stream<Arguments> provideValidatePhysicsControlDigits() {
        return Stream.of(
            Arguments.of("111111111111", "first control digit must be correct"),
            Arguments.of("111111111181", "second control digit must be correct")
        );
    }

    @Test
    void validatePhysicsControlDigits_whenValid() {
        String inn = "111111111130";
        assertDoesNotThrow(() -> InnValidator.validatePhysicsControlDigits(inn));
    }

}