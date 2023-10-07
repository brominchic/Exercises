package ru.brominchik.lessons.threads.feeder;

import org.junit.jupiter.api.Test;
import ru.brominchik.lessons.threads.feeder.atomic.AtomicAnimal;
import ru.brominchik.lessons.threads.feeder.atomic.AtomicFeeder;
import ru.brominchik.lessons.threads.feeder.synchro.SynchroAnimal;
import ru.brominchik.lessons.threads.feeder.synchro.SynchroFeederImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeederTest {
    private static int AMOUNT = 2000;
    private static int TIMEOUT = 300;

    @Test
    void testForSynchro() throws InterruptedException {
        List<SynchroAnimal> animals = new ArrayList<>();
        Feeder feeder = new SynchroFeederImpl(AMOUNT);
        for (int i = 0; i < 5; i++) {
            animals.add(new SynchroAnimal(100, "номер " + (i + 1), feeder, TIMEOUT));
        }
        doTest(AMOUNT, animals, feeder);
    }

    @Test
    void testForAtomic() throws InterruptedException {
        List<AtomicAnimal> animals = new ArrayList<>();
        Feeder feeder = new AtomicFeeder(AMOUNT);
        for (int i = 0; i < 5; i++) {
            animals.add(new AtomicAnimal(100, "номер " + (i + 1), feeder, TIMEOUT));
        }
        doTest(AMOUNT, animals, feeder);
    }

    private void doTest(int amountOfFood, List<? extends AbstractAnimal> animals, Feeder feeder) throws InterruptedException {
        for (AbstractAnimal animal : animals) {
            animal.start();
        }
        for (AbstractAnimal animal : animals) {
            animal.join();
        }
        int eaten = 0;
        for (AbstractAnimal animal : animals) {
            eaten += animal.getEatenAmount();
        }
        assertEquals(amountOfFood, eaten);
        assertEquals(0, feeder.getAmountOfFood());
    }
}
