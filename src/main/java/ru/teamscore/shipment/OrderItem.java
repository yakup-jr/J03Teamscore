package ru.teamscore.shipment;

import java.math.BigDecimal;

public class OrderItem {
    private final Item item;
    private final int quantity;
    private final BigDecimal price;

    private OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.price = item.getPrice();
    }

    public static OrderItem valueOf(Item item, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be more or equal to 1");
        }

        return new OrderItem(item, quantity);
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
