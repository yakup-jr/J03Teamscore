package ru.teamscore.shipment;

import java.util.List;

public class ShipmentMoveDocument extends ShipmentDocument {
    private final StorageInfo moveToStorageInfo;

    public ShipmentMoveDocument(StorageInfo storageInfo, String documentId,
                                   List<OrderItem> items,
                                   StorageInfo moveToStorageInfo) {
        super(storageInfo, documentId, DocumentType.MOVING, items);
        this.moveToStorageInfo = moveToStorageInfo;
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
