package ru.brominchik.lessons.threads.counter.atomic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class AtomicCounterTest {
    @Test
    public void test() throws InterruptedException {
        var threadPool = Executors.newFixedThreadPool(5);
        Map<Integer, String> mapOfValues = new ConcurrentHashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<Runnable> listForRunnable = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listForRunnable.add(new Incrementer(atomicInteger, " номер " + i, mapOfValues, 30));
        }
        listForRunnable.forEach(
                threadPool::submit
        );
        sleep(100);
        for (Map.Entry<Integer, String> pair : mapOfValues.entrySet()) {
            String value = pair.getValue();
            System.out.println(value);
        }
    }

    @Test
    void testForSomeAmount() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Map<Integer, String> mapOfValues = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new Incrementer(atomicInteger, " номер " + i, mapOfValues, 30)));
            threads.get(i).start();
        }
        for (int i = 0; i < 5; i++) {
            threads.get(i).join();
        }
        for (Map.Entry<Integer, String> pair : mapOfValues.entrySet()) {
            String value = pair.getValue();
            System.out.println(value);
        }
    }
}
