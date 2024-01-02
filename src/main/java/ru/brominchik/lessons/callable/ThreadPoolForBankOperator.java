package ru.brominchik.lessons.callable;

import java.util.concurrent.*;

public class ThreadPoolForBankOperator {
    private final ExecutorService executor;

    public ThreadPoolForBankOperator(int maxThreads) {
        executor = Executors.newFixedThreadPool(maxThreads);//создаем тредпул
    }

    public Future<Long> submitBankOperator(BankOperator task) {
        return executor.submit(task); //добавляем в пул BankOperatora
    }
}