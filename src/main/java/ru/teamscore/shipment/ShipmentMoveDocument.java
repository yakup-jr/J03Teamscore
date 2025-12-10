package ru.teamscore.shipment;

import java.math.BigDecimal;
import java.util.List;

public class ShipmentMoveDocument extends ShipmentDocumentFactory {
    private final StorageInfo moveToStorageInfo;

    public ShipmentMoveDocument(StorageInfo storageInfo, String documentId,
                                List<ShipmentItem> items,
                                StorageInfo moveToStorageInfo) {
        super(storageInfo, documentId, DocumentType.MOVING, items);
        this.moveToStorageInfo = moveToStorageInfo;
    }

    @Override
    protected BigDecimal applyPricePerItem(Item item, BigDecimal discount) {
        return item.getPrice();
    }

    /**
     * Является ли перемещение внутренним (между складами одного владельца).
     * Для продаж неприменимо!
     */
    public boolean isInternalMovement() {
        return super.getStorageInfo().getStorageOwner().equals(moveToStorageInfo.getStorageOwner());
    }

    public StorageInfo getMoveToStorage() {
        return moveToStorageInfo;
    }
}
