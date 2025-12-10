package ru.teamscore.randomizer;

import java.util.Scanner;

public class RandomizerUi {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("""
                Выберете генератор:
                1. Бросание кубика (1-6)
                2. Генератор булева масива
                3. Генератор автомобильных номеров""");
            int value = Integer.parseInt(sc.nextLine());
            RandomizerService service = RandomizerService.valueOf(value);

            System.out.println("Введите длину массива: ");
            int length = Integer.parseInt(sc.nextLine());
            System.out.println(service.generateJson(length));
        } catch (IllegalArgumentException e) {
            System.out.printf("Ошибка: %s", e.getMessage());
        }
    }
}
