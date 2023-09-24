package ru.brominchik.lessons.threads.feeder;

public class Animal extends Thread {
    private final Feeder feeder;
    private final int consumption;
    private final String name;

    public Animal(int consumption, String name, Feeder feeder) {
        this.consumption = consumption;
        this.name = name;
        this.feeder = feeder;
    }

    @Override
    public void run() {
        while (feeder.eatFromFeeder(consumption, name)) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        interrupt();
    }

}

