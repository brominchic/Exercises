package ru.brominchik.lessons.threads.feeder;

public class FeederImpl implements Feeder {
    int amountOfFood;

    FeederImpl(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    @Override
    public int getAmountOfFood() {
        return amountOfFood;
    }

    @Override
    public void eatFromFeeder(int amountOfFood) {
        this.amountOfFood = this.amountOfFood - amountOfFood;
    }
}
