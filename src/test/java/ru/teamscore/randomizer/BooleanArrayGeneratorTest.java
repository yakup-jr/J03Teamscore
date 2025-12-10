package ru.teamscore.randomizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BooleanArrayGeneratorTest {
    @Test
    void generate() {
        Boolean[] generated = new BooleanArrayGenerator().generate(100);
        for (Boolean item : generated) {
            assertNotEquals(null, item);
            assertInstanceOf(Boolean.class, item);
        }
    }
}