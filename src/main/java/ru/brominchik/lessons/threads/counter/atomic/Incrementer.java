package ru.brominchik.lessons.threads.counter.atomic;

import java.util.concurrent.ConcurrentHashMap;

public class Incrementer implements Runnable {
    protected final AtomicCounter counter;
    protected final String name;
    private final ConcurrentHashMap<Integer, String> mapOfValues;
    public boolean isAlive;

    public Incrementer(AtomicCounter counter, String name, ConcurrentHashMap<Integer, String> mapOfValues) {
        this.counter = counter;
        this.name = name;
        this.mapOfValues = mapOfValues;
        this.isAlive = true;
    }

    @Override
    public void run() {
        while (isAlive) {
            int i = counter.increase();
            if (i > 0) {
                mapOfValues.put(i, "Я " + name + " выхватил значение " + i);
            } else {
                isAlive = !isAlive;
            }
        }
        System.out.println(name + " завершил работу");

    }

}

