package ru.brominchik.lessons.callable;

import ru.brominchik.lessons.files.BankWorker;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BankOperatorReentrantLocked extends BankOperator {
    private final List<Integer> list;
    private final ReentrantLock locker;

    public BankOperatorReentrantLocked(List<Integer> list, ReentrantLock locker) {
        super(list);
        this.list = list;
        this.locker = locker;

    }

    @Override
    public Long call() {
        locker.lock();
        BankWorker bankWorker = new BankWorker();
        long baseAccount = bankWorker.doOperationsLimited(list, 0, 0);
        locker.unlock();
        return baseAccount;

    }

}
