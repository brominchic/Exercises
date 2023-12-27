package ru.brominchik.lessons.callable;

import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.util.concurrent.Callable;

public  class BankOperator implements Callable<Long> {
    private final File file;
    private final int numOfOperations;
    private final int positionInPool;

    public BankOperator(File file, int numOfOperations, int name) {
        this.file = file;
        this.numOfOperations = numOfOperations;
        this.positionInPool = name;
    }

    @Override
    public Long call() throws Exception {
        BankWorker bankWorker = new BankWorker();
        return bankWorker.doOperationsLimited(file, 0, 0, numOfOperations, positionInPool);
    }
}

