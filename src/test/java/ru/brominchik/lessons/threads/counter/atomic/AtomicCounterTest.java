package ru.brominchik.lessons.threads.counter.atomic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class AtomicCounterTest {
    @Test
    void testForSomeAmount() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        ConcurrentHashMap<Integer, String> mapOfValues = new ConcurrentHashMap<>();
        AtomicCounter counter = new AtomicCounter(100);
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new Incrementer(counter, "номер " + i, mapOfValues)));
            threads.get(i).start();
        }
        for (int i = 0; i < 5; i++) {
            threads.get(i).join();
        }
        for (int i = 1; i < mapOfValues.size(); i++) {
            System.out.println(mapOfValues.get(i));
        }

    }

}
