package ru.teamscore.randomizer;

import com.google.gson.Gson;

public class RandomizerService {
    private final ArrayGenerator<?> generator;
    private final Gson gson;

    private RandomizerService(ArrayGenerator<?> generator) {
        this.generator = generator;
        this.gson = new Gson();
    }

    public static RandomizerService valueOf(int generatorType) {
        ArrayGenerator<?> generator = switch (generatorType) {
            case 1 -> new DieRollGenerator();
            case 2 -> new BooleanArrayGenerator();
            case 3 -> new CarNumberGenerator();
            default -> throw new IllegalArgumentException("Такой пункт меню отсутствует");
        };
        return new RandomizerService(generator);
    }

    public String generateJson(int size) {
        return gson.toJson(generator.generate(size));
    }
}
