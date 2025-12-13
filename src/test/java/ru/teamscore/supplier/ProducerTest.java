package ru.teamscore.supplier;

import org.junit.jupiter.api.Test;
import ru.teamscore.supplier.inn.Inn;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProducerTest {

    @Test
    void getFinalPrice_returnsSamePrice() {
        Address address = new Address("Russia", "Samara", "Street", "Building");
        Producer producer = new Producer(Inn.valueOf("1111111117"), "Producer", address);
        
        BigDecimal price = new BigDecimal("100");
        assertEquals(price, producer.getFinalPrice(price));
    }
}
