package ru.brominchik.lessons.threads.feeder;

public class Animal extends Thread {
    private final Feeder feeder;
    private final int consumption;
    private final String name;
    private boolean isAlive;

    public Animal(int consumption, String name, Feeder feeder) {
        this.consumption = consumption;
        this.name = name;
        this.feeder = feeder;
        this.isAlive = true;
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                eat();
            } catch (InterruptedException ignored) {

            }
        }

    }

    public synchronized void eat() throws InterruptedException {
        synchronized (Animal.class) {
            System.out.println(name + " попытался поесть");
            if (consumption <= feeder.getAmountOfFood()) {
                feeder.setAmountOfFood(consumption);
                System.out.println(name + " сьел " + consumption + ". Осталось " + feeder.getAmountOfFood());
            } else {
                if (feeder.getAmountOfFood() != 0) {
                    System.out.println(name + " не сьел");
                }
                this.isAlive = false;
                System.out.println(name + " завершил работу");
            }
            wait(10);
        }
    }
}



