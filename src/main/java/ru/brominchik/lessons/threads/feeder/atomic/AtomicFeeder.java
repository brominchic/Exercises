package ru.brominchik.lessons.threads.feeder.atomic;

import ru.brominchik.lessons.threads.feeder.Feeder;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicFeeder implements Feeder {
    private final AtomicInteger amountOfFood;

    public AtomicFeeder(int amountOfFood) {
        this.amountOfFood = new AtomicInteger(amountOfFood);
    }

    @Override
    public int getAmountOfFood() {
        return amountOfFood.get();
    }

    @Override
    public boolean eatFromFeeder(int consumption) {
        int expectedValue = this.amountOfFood.get();
        if (expectedValue >= consumption) {
            return this.amountOfFood.compareAndSet(expectedValue, expectedValue - consumption);
        }
        return false;
    }
}



