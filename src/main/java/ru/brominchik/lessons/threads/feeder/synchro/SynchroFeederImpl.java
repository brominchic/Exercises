package ru.brominchik.lessons.threads.feeder.synchro;

import ru.brominchik.lessons.threads.feeder.Feeder;

public class SynchroFeederImpl implements Feeder {
    int amountOfFood;

    public SynchroFeederImpl(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    @Override
    public int getAmountOfFood() {
        return amountOfFood;
    }

    @Override
    public boolean eatFromFeeder(int amountOfFood) {
        this.amountOfFood = this.amountOfFood - amountOfFood;
        return true;
    }
}
