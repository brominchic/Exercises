package ru.brominchik.lessons.callable_work;

import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.util.concurrent.Callable;

public  class BankOperator implements Callable<Long> {
    private final File file;
    private final int numOfOperations;

    public BankOperator(File file, int numOfOperations) {
        this.file = file;
        this.numOfOperations = numOfOperations;
    }

    @Override
    public Long call() throws Exception {
        BankWorker bankWorker = new BankWorker();
        long baseAccount = bankWorker.doOperationsLimited(file, 0, 0, numOfOperations);
        return baseAccount;
    }
}

