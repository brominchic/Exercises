package ru.brominchik.lessons.threads.feeder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeederTest {
    @Test
    void testForSomeAmount() throws InterruptedException {
        List<Animal> animals = new CopyOnWriteArrayList<>();
        Feeder feeder = new Feeder(2000);
        for (int i = 0; i < 5; i++) {
            animals.add(new Animal((i + 1) * 10, "номер " + (i + 1), feeder));
            animals.get(i).start();
        }
        for (Animal animal : animals) {
            animal.join();
        }
        assertEquals(0, feeder.amountOfFood);
    }

}
