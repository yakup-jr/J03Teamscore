package ru.teamscore.shipment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ShipmentSaleDocument extends ShipmentDocumentFactory {
    private final String customer;

    protected ShipmentSaleDocument(StorageInfo storageInfo, String documentId,
                                   List<ShipmentItem> items, String customer) {
        super(storageInfo, documentId, DocumentType.SALE, items);
        this.customer = customer;
    }

    @Override
    protected BigDecimal applyPricePerItem(Item item, BigDecimal discount) {
        return item.getPrice().multiply(new BigDecimal("1").subtract(discount))
            .setScale(2, RoundingMode.CEILING);
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

        for (ShipmentItem item : super.getItems()) {
            if (item.getQuantity() > minQuantity) {
                return true;
            }
            sumQuantity += item.getQuantity();
        }

        return sumQuantity >= minQuantity;
    }
}
