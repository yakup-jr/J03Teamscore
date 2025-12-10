package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipmentDocumentTest {

    private static final ShipmentDocument document =
        new ShipmentDocument(StorageInfo.valueOf("someName", "someOwner"),
            "someDocumentId", DocumentType.MOVING,
            List.of(OrderItem.valueOf(Item.valueOf("someId1", "someArticle1", "someTitle1",
                    new BigDecimal("99.99")), 9),
                OrderItem.valueOf(Item.valueOf("someId2", "someArticle2", "someTitle2",
                    new BigDecimal("46.87")), 6)));

    @Test
    void totalAmount() {
        assertEquals(1181.13, document.totalAmount());
    }

    @Test
    void itemAmount() {
        assertEquals(281.22, document.itemAmount("someId2"));
    }

    @Test
    void promoSum() {
        assertEquals(899.91, document.promoSum(new String[]{"someArticle1"}));
    }
}
