package ru.teamscore.shipment;

import java.util.List;

public class ShipmentSaleDocument extends ShipmentDocument {
    private final String customer;

    protected ShipmentSaleDocument(StorageInfo storageInfo, String documentId,
                                   List<OrderItem> items, String customer) {
        super(storageInfo, documentId, DocumentType.SALE, items);
        this.customer = customer;
    }

    /**
     * Является ли продажа оптовой для переданного минимального объема.
     * Для перемещений неприменимо!
     */
    public boolean isWholesale(double minQuantity) {
        if (super.getDocumentType() == DocumentType.MOVING) {
            return false;
        }
        double sumQuantity = 0;

        for (OrderItem item : super.getItems()) {
            if (item.getQuantity() > minQuantity) {
                return true;
            }
            sumQuantity += item.getQuantity();
        }

        return sumQuantity >= minQuantity;
    }
}
