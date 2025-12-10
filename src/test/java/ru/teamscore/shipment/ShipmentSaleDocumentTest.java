package ru.teamscore.shipment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipmentSaleDocumentTest {
    private static final ShipmentDocument document =
        new ShipmentDocument(StorageInfo.valueOf("someName", "someOwner"),
            "someDocumentId", DocumentType.SALE,
            List.of(ShipmentItem.valueOf(Item.valueOf("someId1", "someArticle1", "someTitle1",
                    new BigDecimal("99.99")), 9),
                ShipmentItem.valueOf(Item.valueOf("someId2", "someArticle2", "someTitle2",
                    new BigDecimal("46.87")), 6)));

    @ParameterizedTest
    @MethodSource("provideIsWholesale")
    void isWholesale_whenMoreMinQuantity_returnTrue(int minQuantity, boolean expected) {
        ShipmentSaleDocument shipmentSaleDocument =
            new ShipmentSaleDocument(document.getStorageInfo(), document.getDocumentId(),
                document.getItems(), "someCustomer");

        assertEquals(expected, shipmentSaleDocument.isWholesale(minQuantity));
    }

    private static Stream<Arguments> provideIsWholesale() {
        return Stream.of(
            Arguments.of(10, true),
            Arguments.of(15, true),
            Arguments.of(16, false)
        );
    }
}
