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
        locker.lock();//блокируем работу других потоков
        BankWorker bankWorker = new BankWorker();
        long finalBalance = bankWorker.doOperationsFromList(list);// создаем банк воркера,передаем ему его часть массива
        locker.unlock();//разблокируем работу других потоков
        return finalBalance;

    }

}
