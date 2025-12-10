package ru.teamscore.randomizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DieRollGeneratorTest {

    @Test
    void generate() {
        Integer[] generated = new DieRollGenerator().generate(100);
        for (Integer item : generated) {
            assertEquals(3.5, item, 2.5);
        }
    }
}
