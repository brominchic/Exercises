package ru.brominchik.lessons.threads.feeder;

public class Animal extends AbstractAnimal {

    public Animal(int consumption, String name, Feeder feeder) {
        super(feeder, consumption, name);
    }

    @Override
    public int eat() {
        synchronized (feeder) {
            if (feeder.getAmountOfFood() == 0) {
                this.isAlive = false;
                return 0;
            } else {
                if (consumption <= feeder.getAmountOfFood()) {
                    feeder.eatFromFeeder(consumption);
                    System.out.println(name + " сьел " + consumption + ". Осталось " + feeder.getAmountOfFood());
                    return consumption;
                } else {
                    System.out.println(name + " не сьел");
                    this.isAlive = false;
                    return 0;
                }
            }
        }
    }
}



