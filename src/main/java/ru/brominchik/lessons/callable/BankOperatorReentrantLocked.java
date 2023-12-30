package ru.brominchik.lessons.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class BankOperatorReentrantLocked extends BankOperator {
    private static final Logger logger = LoggerFactory.getLogger(BankOperator.class);


    private final File file;
    private final int numOfOperations;
    private final ReentrantLock locker;
    private final int positionInPool;
    private final int numOfThreadsInPool;

    public BankOperatorReentrantLocked(File file, int numOfOperations, ReentrantLock locker, int positionInPool, int numOfThreads) {
        super(file, numOfOperations, positionInPool);
        this.file = file;
        this.numOfOperations = numOfOperations;
        this.locker = locker;
        this.positionInPool = positionInPool;
        this.numOfThreadsInPool = numOfThreads;
    }

    @Override
    public Long call() throws IOException {
        try {
            Thread.sleep((numOfThreadsInPool - positionInPool) * 1000L);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        locker.lock();
        long baseAccount = 0;
        BankWorker bankWorker = new BankWorker();
        long finalAccount = 0;
        logger.info(positionInPool + " начал работу");
        baseAccount = bankWorker.doOperationsLimited(file, baseAccount, finalAccount, numOfOperations, positionInPool);
        locker.unlock();
        return baseAccount;

    }

}
