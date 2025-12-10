package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentMoveDocumentTest {
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
    void isInternalMovement_whenInternalMovement_returnTrue() {
        StorageInfo moveToStorage = StorageInfo.valueOf("someName2", "someOwner");

        ShipmentMoveDocument shipmentMoveDocument =
            new ShipmentMoveDocument(document.getStorageInfo(), document.getDocumentId(),
                document.getItems(), moveToStorage);

        assertTrue(shipmentMoveDocument.isInternalMovement());
    }

    @Test
    void isInternalMovement_whenExternalMovement_returnFalse() {
        StorageInfo moveToStorage = StorageInfo.valueOf("someName2", "someElseOwner");
        ShipmentMoveDocument shipmentMoveDocument =
            new ShipmentMoveDocument(document.getStorageInfo(), document.getDocumentId(),
                document.getItems(), moveToStorage);

        assertFalse(shipmentMoveDocument.isInternalMovement());
    }

    @Test
    void applyPricePerItem() {
        ShipmentMoveDocument shipmentSaleDocument =
            new ShipmentMoveDocument(document.getStorageInfo(), document.getDocumentId(),
                document.getItems(), StorageInfo.valueOf("someName2", "someOwner2"));

        Item item = shipmentSaleDocument.getItems().getLast().getItem();

        assertEquals(item.getPrice(), shipmentSaleDocument.applyPricePerItem(
            item, new BigDecimal("0.1")));
    }

    @Test
    void promoSum() {
        assertEquals(899.91, document.promoSum(new String[]{"someArticle1"},
            new BigDecimal("0.1")));
    }
}
