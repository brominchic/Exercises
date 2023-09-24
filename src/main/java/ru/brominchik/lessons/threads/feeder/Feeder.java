package ru.brominchik.lessons.threads.feeder;

public class Feeder {
    int amountOfFood;

    Feeder(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    public synchronized boolean eatFromFeeder(int amountOfEatenFood, String name) {
        if (this.amountOfFood >= amountOfEatenFood) {
            this.amountOfFood = this.amountOfFood - amountOfEatenFood;
            System.out.println("я " + name + " сьел " + amountOfEatenFood + ". " + "Осталось = " + this.amountOfFood);
            return true;
        }
        if (this.amountOfFood == 0) {
            return false;
        }
        System.out.println(name + " не сьел");
        return false;
    }

}
