package ru.teamscore.randomizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarNumberGeneratorTest {

    @Test
    void generate() {
        RussianCarNumber[] generated = new CarNumberGenerator().generate(100);
        for (RussianCarNumber item : generated) {
            assertNotNull(item);
            assertDoesNotThrow(() -> RussianCarNumber.valueOf(item.getSeries(), item.getNumber(), item.getRegion()));
        }
    }
}
