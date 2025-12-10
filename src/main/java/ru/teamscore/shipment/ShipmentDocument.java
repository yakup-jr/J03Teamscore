package ru.teamscore.shipment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Документ отгрузки со склада.
 * Бывает двух типов: перемещение (на другой склад) и продажа (покупателю).
 *
 */
class ShipmentDocument {
    private final StorageInfo storageInfo;
    private final String documentId; // GUID документа
    private final LocalDateTime documentDate; // дата документа
    private final DocumentType documentType; // тип отгрузки: sale - продажа, moving - перемещение
    private final List<OrderItem> items;

    protected ShipmentDocument(StorageInfo storageInfo, String documentId, DocumentType documentType,
                               List<OrderItem> items) {
        this.storageInfo = storageInfo;
        this.documentId = documentId;
        this.documentDate = LocalDateTime.now();
        this.documentType = documentType;
        this.items = items;
    }

    /**
     * Суммарная стоимость товаров в документе.
     */
    protected double totalAmount() {
        BigDecimal sum = new BigDecimal("0");

        for (OrderItem item : items) {
            sum = sum.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        return sum.doubleValue();
    }

    /**
     * Стоимость товара с переданным id.
     */
    protected double itemAmount(String id) {
        for (OrderItem item : items) {
            if (item.getItem().getId().equals(id)) {
                return item.getPrice().multiply(new BigDecimal(item.getQuantity())).doubleValue();
            }
        }

        return 0;
    }

    /**
     * Суммарная стоимость товаров, попадающих в список промо-акции.
     */
    protected double promoSum(String[] promoArticles) {
        BigDecimal sum = new BigDecimal("0");

        for (OrderItem item : items) {
            for (String promoArticle : promoArticles) {
                if (item.getItem().getArticle().equals(promoArticle)) {
                    sum = sum.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                    break;
                }
            }
        }

        return sum.doubleValue();
    }

    protected StorageInfo getStorageInfo() {
        return storageInfo;
    }

    protected String getDocumentId() {
        return documentId;
    }

    protected LocalDateTime getDocumentDate() {
        return documentDate;
    }

    protected DocumentType getDocumentType() {
        return documentType;
    }

    protected List<OrderItem> getItems() {
        return items;
    }
}