package ru.brominchik.lessons.threads.feeder.atomic;

import ru.brominchik.lessons.threads.feeder.AbstractAnimal;
import ru.brominchik.lessons.threads.feeder.Feeder;

public class AtomicAnimal extends AbstractAnimal {
    public AtomicAnimal(int consumption, String name, Feeder feeder, int timeout) {
        super(feeder, consumption, name, timeout);
    }

    @Override
    public int eat() {
        if (feeder.eatFromFeeder(consumption)) {
            return consumption;
        }
        return 0;
    }
}
