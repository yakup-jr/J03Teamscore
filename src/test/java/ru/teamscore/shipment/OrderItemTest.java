package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {

    @Test
    void valueOf_whenInvalid_throwIllegalArgumentException() {
        Item item = Item.valueOf("someId", "someArticle", "someTitle", new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> OrderItem.valueOf(item, 0),
            "Quantity must be more or equal to 1");
    }

    @Test
    void valueOf_whenValid_returnOrderItem() {
        Item item = Item.valueOf("someId", "someArticle", "someTitle", new BigDecimal("100"));
        assertDoesNotThrow(() -> OrderItem.valueOf(item, 1));
    }
}
