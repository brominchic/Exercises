package ru.brominchik.lessons.threads.feeder.synchro;

import ru.brominchik.lessons.threads.feeder.AbstractAnimal;
import ru.brominchik.lessons.threads.feeder.Feeder;

public class SynchroAnimal extends AbstractAnimal {

    public SynchroAnimal(int consumption, String name, Feeder feeder, int timeout) {
        super(feeder, consumption, name, timeout);
    }

    @Override
    public int eat() {
        synchronized (feeder) {
            if (consumption <= feeder.getAmountOfFood()) {
                feeder.eatFromFeeder(consumption);
                System.out.println(name + " сьел " + consumption + ". Осталось " + feeder.getAmountOfFood());
                return consumption;
            } else {
                System.out.println(name + " не сьел");
                return 0;
            }
        }
    }
}



