package ru.brominchik.lessons.callableWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.util.concurrent.Callable;

public  class BankOperator implements Callable<Long> {
    private static final Logger logger = LoggerFactory.getLogger(BankOperator.class);
    private File file;
    private int numOfOperations;

    public BankOperator(File file, int numOfOperations) {
        this.file = file;
        this.numOfOperations = numOfOperations;
    }

    @Override
    public Long call() throws Exception {
        BankWorker bankWorker = new BankWorker();
        File file = bankWorker.createFile(numOfOperations, this.file.getPath());
        long baseAccount = 0;
        long finalAccount = 0;
        baseAccount=bankWorker.doOperations(file, baseAccount, finalAccount);
        return baseAccount;
    }
}

