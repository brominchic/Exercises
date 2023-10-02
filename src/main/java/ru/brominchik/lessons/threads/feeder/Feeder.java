package ru.brominchik.lessons.threads.feeder;

public class Feeder {
    int amountOfFood;
    public boolean isEnoughFood;

    Feeder(int amountOfFood) {
        this.amountOfFood = amountOfFood;
        this.isEnoughFood = true;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public void setAmountOfFood(int amountOfFood) {
        this.amountOfFood = this.amountOfFood - amountOfFood;
    }
}
