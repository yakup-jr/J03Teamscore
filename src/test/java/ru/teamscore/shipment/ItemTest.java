package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    @ParameterizedTest
    @MethodSource("provideValueOf")
    void valueOf_whenInvalid_throwIllegalArgumentException(String id, String article,
                                                           String title, BigDecimal price,
                                                           String exceptionMessage) {
        assertThrows(IllegalArgumentException.class, () -> Item.valueOf(id, article, title, price),
            exceptionMessage);
    }

    private static Stream<Arguments> provideValueOf() {
        return Stream.of(
            Arguments.of("    ", "someArticle", "someTitle", new BigDecimal("100"),
                "Id must be not blank"),
            Arguments.of("someId", "   ", "someTitle", new BigDecimal("100"),
                "Article must be not blank"),
            Arguments.of("someId", "someArticle", "   ", new BigDecimal("100"),
                "Title must be not blank"),
            Arguments.of("someId", "someArticle", "someTitle", new BigDecimal("0"),
                "Price must be positive")
        );
    }

    @Test
    void valueOf_whenValid_returnItem() {
        assertDoesNotThrow(() ->
            Item.valueOf("someId", "someArticle", "someTitle", new BigDecimal("100")));
    }
}
