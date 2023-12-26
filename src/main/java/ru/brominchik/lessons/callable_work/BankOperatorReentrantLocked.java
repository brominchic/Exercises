package ru.brominchik.lessons.callable_work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class BankOperatorReentrantLocked extends BankOperator {
    private final File file;
    private final int numOfOperations;
    private final ReentrantLock locker;
    private final int name;
    private static final Logger logger = LoggerFactory.getLogger(BankOperator.class);
    private final int numOfThreads;

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
            Thread.sleep((numOfThreads - name) * 1000L);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        locker.lock();
        long baseAccount = 0;
        BankWorker bankWorker = new BankWorker();
        File file = bankWorker.createFile(numOfOperations, this.file.getPath());
        long finalAccount = 0;
        logger.info(name + " начал работу");
        baseAccount = bankWorker.doOperationsLimited(file, baseAccount, finalAccount, numOfOperations);
        locker.unlock();
        return baseAccount;

    }

}
