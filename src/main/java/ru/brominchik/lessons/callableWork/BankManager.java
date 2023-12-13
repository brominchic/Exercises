package ru.brominchik.lessons.callableWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BankManager {
    private static final Logger logger = LoggerFactory.getLogger(BankManager.class);
    ReentrantLock locker= new ReentrantLock();

    public void doOperate(int numOfThreads,int numOfOperations) throws ExecutionException, InterruptedException {
        long sum = 0;
        ExecutorService service = Executors.newFixedThreadPool(numOfThreads);
        List<Future<Long>> futures = new ArrayList<>();
        for(int i = 0; i < numOfThreads; i++)
        {
            String path = "C:\\Users\\text.txt";
            Future<Long> future = service.submit(new BankOperator(new File(path),numOfOperations));
            futures.add(future);
        }
        for(Future<Long> fut : futures){
            sum += fut.get();
        }
        logger.info("Всего: "+sum);
    }
    public void doOperateLocked(int numOfThreads,int numOfOperations) throws ExecutionException, InterruptedException {
        long sum = 0;
        ExecutorService service = Executors.newFixedThreadPool(numOfThreads);
        List<Future<Long>> futures = new ArrayList<>();
        Condition condition = locker.newCondition();
        for(int i = 0; i < numOfThreads; i++)
        {
            String path = "C:\\Users\\text.txt";
            Future<Long> future = service.submit(new BankOperatorReentrantLocked(new File(path),numOfOperations,locker, i,numOfThreads));
            futures.add(future);
        }

        for(Future<Long> fut : futures){
            sum += fut.get();
        }

        logger.info("Всего: "+sum);
    }

}
