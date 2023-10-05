package ru.brominchik.lessons.threads.feeder;

public class Feeder {
    int amountOfFood;

    Feeder(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public void setAmountOfFood(int amountOfFood) {
        this.amountOfFood = this.amountOfFood - amountOfFood;
    }
}
