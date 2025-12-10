package ru.teamscore.shipment;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final List<OrderItem> items;
    private final LocalDateTime created;

    private Order(List<OrderItem> items) {
        this.items = items;
        this.created = LocalDateTime.now();
    }

    public static Order valueOf(List<OrderItem> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Items must have at least 1 item");
        }

        return new Order(items);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
