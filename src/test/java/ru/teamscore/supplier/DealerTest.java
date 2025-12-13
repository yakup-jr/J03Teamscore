package ru.teamscore.supplier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.teamscore.supplier.inn.Inn;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DealerTest {
    private static final Address address = new Address("Russia", "Samara", "Street", "Building");
    private static final Inn inn = Inn.valueOf("1111111117");
    private static final Producer producer = new Producer(inn, "Producer", address);


    @Test
    void valueOf_whenNegativeMargin_throwIllegalArgumentException() {
        BigDecimal marginPercent = new BigDecimal("-1");
        assertThrows(IllegalArgumentException.class,
            () -> Dealer.valueOf(inn, "Dealer", address, marginPercent, producer),
            "Margin percent must be positive");
    }

    @ParameterizedTest
    @MethodSource("provideGetFinalPrice")
    void getFinalPrice_returnsCorrectPrice(BigDecimal basePrice, BigDecimal marginPercent,
                                           BigDecimal expectedPrice) {
        Dealer dealer =
            Dealer.valueOf(inn, "Dealer", address, marginPercent, producer);

        assertEquals(expectedPrice, dealer.getFinalPrice(basePrice));
    }

    private static Stream<Arguments> provideGetFinalPrice() {
        return Stream.of(
            Arguments.of(new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("110.00")),
            Arguments.of(new BigDecimal("200"), new BigDecimal("10"), new BigDecimal("220.00")),
            Arguments.of(new BigDecimal("100"), new BigDecimal("20"), new BigDecimal("120.00"))
        );
    }
}
