package ru.brominchik.lessons.threads.feeder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeederImplTest {
    @Test
    void testForSomeAmount() throws InterruptedException {
        List<Animal> animals = new ArrayList<>();
        int amountOfFood = 2000;
        Feeder feeder = new FeederImpl(amountOfFood);
        for (int i = 0; i < 5; i++) {
            animals.add(new Animal((i + 1) * 10, "номер " + (i + 1), feeder));
            animals.get(i).start();
        }
        for (Animal animal : animals) {
            animal.join();
        }

        int eaten = 0;
        for (Animal animal : animals) {
            eaten += animal.getEatenAmount();
        }
        assertEquals(amountOfFood, eaten);
        assertEquals(0, feeder.getAmountOfFood());
    }

}
