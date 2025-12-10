package ru.teamscore.randomizer;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class DieRollGenerator implements ArrayGenerator<Integer> {
    @Override
    public Integer[] generate(int size) {
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(1, 7);
        }

        return Arrays.stream(arr).boxed().toArray(Integer[]::new);
    }
}
