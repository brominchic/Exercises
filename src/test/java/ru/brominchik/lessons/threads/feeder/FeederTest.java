package ru.brominchik.lessons.threads.feeder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeederTest {
    @Test
    void testForSomeAmount() throws InterruptedException {
        List<Animal> animals = new ArrayList<>();
        Feeder feeder = new Feeder(2000);
        for (int i = 0; i < 5; i++) {
            animals.add(new Animal((i + 1) * 10, "номер " + (i + 1), feeder));
            animals.get(i).start();
        }
        for (int i = 0; i < 5; i++) {
            animals.get(i).join();
        }
        assertEquals(0, feeder.amountOfFood);
    }

}
