package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    @Test
    void valueOf_whenItemsEmpty_thrownIllegalArgumentException() {
        List<OrderItem> orderItems = new ArrayList<>();
        assertThrows(IllegalArgumentException.class,
            () -> Order.valueOf(orderItems), "Items must have at least 1 item");
    }

    @Test
    void valueOf_whenValid_returnOrder() {
        Item item = Item.valueOf("someId", "someArticle", "someTitle", new BigDecimal("100"));
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(OrderItem.valueOf(item, 1));
        assertDoesNotThrow(() -> Order.valueOf(orderItems));
    }
}
