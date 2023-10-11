package ru.brominchik.lessons.threads.counter.atomic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class AtomicCounterTest {
    @Test
    void name() throws InterruptedException {
        ArrayList<Thread> increasers = new ArrayList<>();
        ConcurrentHashMap<Integer, String> mapOfValues = new ConcurrentHashMap<>();
        AtomicCounter counter = new AtomicCounter(30);
        for (int i = 0; i < 5; i++) {
            increasers.add(new Thread(new Increaser(counter, "номер " + i, 30, mapOfValues)));
            increasers.get(i).start();
        }
        for (int i = 0; i < 5; i++) {
            increasers.get(i).join();
        }
        System.out.println(mapOfValues);
    }
}
