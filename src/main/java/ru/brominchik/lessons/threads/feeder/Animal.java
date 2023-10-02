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
        while (feeder.isEnoughFood) {
            try {
                eat();
            } catch (InterruptedException ignored) {

            }
        }

    }

    public void eat() throws InterruptedException {
        synchronized (feeder) {
            if (feeder.getAmountOfFood() == 0) {
                System.out.println(name + " завершил работу");
                feeder.isEnoughFood = false;
                return;
            } else {
                System.out.println(name + " попытался поесть");
                if (consumption <= feeder.getAmountOfFood()) {
                    feeder.setAmountOfFood(consumption);
                    System.out.println(name + " сьел " + consumption + ". Осталось " + feeder.getAmountOfFood());
                } else {
                    System.out.println(name + " не сьел");
                    System.out.println(name + " завершил работу");
                }
            }
        }
        Thread.sleep(300);
    }
}



