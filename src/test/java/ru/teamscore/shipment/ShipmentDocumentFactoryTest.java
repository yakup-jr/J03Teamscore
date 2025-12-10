package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipmentDocumentFactoryTest {

    private static final ShipmentDocumentFactory document =
        new ShipmentDocumentFactory(StorageInfo.valueOf("someName", "someOwner"),
            "someDocumentId", DocumentType.MOVING,
            List.of(ShipmentItem.valueOf(Item.valueOf("someId1", "someArticle1", "someTitle1",
                    new BigDecimal("99.99")), 9),
                ShipmentItem.valueOf(Item.valueOf("someId2", "someArticle2", "someTitle2",
                    new BigDecimal("46.87")), 6))) {
            @Override
            protected BigDecimal applyPricePerItem(Item item, BigDecimal discount) {
                return item.getPrice();
            }
        };

    @Test
    void totalAmount() {
        assertEquals(1181.13, document.totalAmount());
    }

    @Test
    void itemAmount() {
        assertEquals(281.22, document.itemAmount("someId2"));
    }
}
