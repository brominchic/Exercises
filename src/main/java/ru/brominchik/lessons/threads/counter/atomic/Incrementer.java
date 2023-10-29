package ru.brominchik.lessons.threads.counter.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Incrementer implements Runnable {
    private final AtomicInteger atomicInteger;
    protected final String name;
    private final Map<Integer, String> mapOfValues;
    private final int finalNumber;
    private static final Logger logger = LoggerFactory.getLogger(Incrementer.class);


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

    private void increase(int expectedValue) {
        if (atomicInteger.compareAndSet(expectedValue, expectedValue + 1)) {
            mapOfValues.put(atomicInteger.intValue(), " Я " + name + " выхватил значение " + (expectedValue + 1));
            logger.info(" Я " + name + " выхватил значение " + (expectedValue + 1));
        }

    }
}


