package ru.brominchik.lessons.callableWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BankOperatorReentrantLocked extends BankOperator {
    private File file;
    private int numOfOperations;
    private ReentrantLock locker;
    private int name;
    private static final Logger logger = LoggerFactory.getLogger(BankOperator.class);
    private int numOfThreads;

    public BankOperatorReentrantLocked(File file, int numOfOperations, ReentrantLock locker, int name, int numOfThreads) {
        super(file, numOfOperations);
        this.file = file;
        this.numOfOperations = numOfOperations;
        this.locker = locker;
        this.name = name;
        this.numOfThreads=numOfThreads;
    }

    @Override
    public Long call() throws InterruptedException, IOException {
        try {
            Thread.sleep((numOfThreads-name) * 1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        locker.lock();
        BankWorker bankWorker = new BankWorker();
        File file = bankWorker.createFile(numOfOperations, this.file.getPath());
        long baseAccount = 0;
        long finalAccount = 0;
        logger.info(name + " начал работу");
        baseAccount = bankWorker.doOperations(file, baseAccount, finalAccount);
        locker.unlock();
        return baseAccount;

    }

}
