package ru.teamscore.shipment;

import java.math.BigDecimal;

public class ShipmentItem {
    private final Item item;
    private final int quantity;
    private final BigDecimal price;

    private ShipmentItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.price = item.getPrice();
    }

    public static ShipmentItem valueOf(Item item, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be more or equal to 1");
        }

        return new ShipmentItem(item, quantity);
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
