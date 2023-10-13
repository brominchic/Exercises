package ru.brominchik.lessons.threads.counter.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger number;
    private final int finalNumber;

    public AtomicCounter(int finalNumber) {
        this.number = new AtomicInteger(0);
        this.finalNumber = finalNumber;
    }

    public int increase() {
        int expectedValue = this.number.get();
        if (expectedValue <= finalNumber) {
            return this.number.incrementAndGet();
        }
        return 0;
    }
}

