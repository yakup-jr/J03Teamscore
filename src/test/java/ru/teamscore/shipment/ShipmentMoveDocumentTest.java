package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShipmentMoveDocumentTest {
    private static final ShipmentDocument document =
        new ShipmentDocument(StorageInfo.valueOf("someName", "someOwner"),
            "someDocumentId", DocumentType.MOVING,
            List.of(ShipmentItem.valueOf(Item.valueOf("someId1", "someArticle1", "someTitle1",
                    new BigDecimal("99.99")), 9),
                ShipmentItem.valueOf(Item.valueOf("someId2", "someArticle2", "someTitle2",
                    new BigDecimal("46.87")), 6)));

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
}
