package ru.brominchik.lessons.callable;

import ru.brominchik.lessons.files.BankWorker;

import java.util.List;
import java.util.concurrent.Callable;

public  class BankOperator implements Callable<Long> {
    private final List<Integer> list;

    public BankOperator(List<Integer> list) {
        this.list = list;
    }


    @Override
    public Long call() {
        BankWorker bankWorker = new BankWorker();
        return bankWorker.doOperationsFromList(list); // создаем банк воркера,передаем ему его часть массива
    }
}

