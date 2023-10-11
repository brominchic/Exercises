package ru.brominchik.lessons.threads.counter.atomic;

import java.util.concurrent.ConcurrentHashMap;

public class Increaser implements Runnable {
    protected final AtomicCounter counter;
    protected final String name;
    private final int finalNumber;
    private final ConcurrentHashMap<Integer, String> mapOfValues;

    public Increaser(AtomicCounter counter, String name, int finalNumber, ConcurrentHashMap<Integer, String> mapOfValues) {
        this.counter = counter;
        this.name = name;
        this.finalNumber = finalNumber;
        this.mapOfValues = mapOfValues;
    }

    @Override
    public void run() {
        while (counter.getNumber() < finalNumber) {
            int i = counter.increase();
            mapOfValues.put(i, "Я " + name + " выхватил значение " + i);
        }
        System.out.println(name + " завершил работу");

    }

}

