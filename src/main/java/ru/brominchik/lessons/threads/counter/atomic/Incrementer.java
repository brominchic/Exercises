package ru.brominchik.lessons.threads.counter.atomic;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Incrementer implements Runnable {
    private final AtomicInteger atomicInteger;
    protected final String name;
    private final Map<Integer, String> mapOfValues;
    private final int finalNumber;

    public Incrementer(AtomicInteger atomicInteger, String name, Map<Integer, String> mapOfValues, int finalNumber) {
        this.atomicInteger = atomicInteger;
        this.name = name;
        this.mapOfValues = mapOfValues;
        this.finalNumber = finalNumber;
    }

    @Override
    public void run() {
        while (atomicInteger.intValue() < finalNumber) {
            int expectedValue = atomicInteger.intValue();
            if (expectedValue < finalNumber) {
                increase(expectedValue);
            }
        }

    }

    private boolean increase(int expectedValue) {
        if (atomicInteger.compareAndSet(expectedValue, expectedValue + 1)) {
            mapOfValues.put(atomicInteger.intValue(), " Я " + name + " выхватил значение " + (expectedValue + 1));
            return true;
        }
        return false;
    }
}


