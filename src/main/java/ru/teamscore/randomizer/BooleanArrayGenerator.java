package ru.teamscore.randomizer;

import java.util.concurrent.ThreadLocalRandom;

public class BooleanArrayGenerator implements ArrayGenerator<Boolean> {
    @Override
    public Boolean[] generate(int size) {
        Boolean[] booleans = new Boolean[size];

        for (int i = 0; i < size; i++) {
            booleans[i] = ThreadLocalRandom.current().nextBoolean();
        }

        return booleans;
    }
}
