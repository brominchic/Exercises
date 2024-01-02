package ru.brominchik.lessons.callable;

public class Account {
    private final String name;
    private int numOfCurrency;

    public Account(int numOfCurrency, String name) {
        this.numOfCurrency = numOfCurrency;//число валюты на счету
        this.name = name;//имя счета
    }

    public int getNumOfCurrency() {
        return numOfCurrency;
    }

    public synchronized void setNumOfCurrency(int numOfCurrency) {
        this.numOfCurrency += numOfCurrency;
    }

}
