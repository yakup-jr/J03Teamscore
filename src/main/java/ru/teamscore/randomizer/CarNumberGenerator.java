package ru.teamscore.randomizer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CarNumberGenerator implements ArrayGenerator<RussianCarNumber> {
    @Override
    public RussianCarNumber[] generate(int size) {
        RussianCarNumber[] carNumber = new RussianCarNumber[size];

        for (int i = 0; i < carNumber.length; i++) {
            carNumber[i] =
                RussianCarNumber.valueOf(generateSeries(), generateNumber(), generateRegion());
        }

        return carNumber;
    }

    private String generateSeries() {
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> characters = new HashMap<>();
        String seq = "АВЕКМНОРСТУХ";

        for (int i = 0; i < seq.length(); i++) {
            characters.put(i + 1, String.valueOf(seq.charAt(i)));
        }

        for (int i = 0; i < 3; i++) {
            sb.append(characters.get(ThreadLocalRandom.current().nextInt(1, seq.length() + 1)));
        }

        return sb.toString();
    }

    private String generateNumber() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        sb.append(ThreadLocalRandom.current().nextInt(1, 10));

        return sb.toString();
    }

    private String generateRegion() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(1, 1000));
    }
}
