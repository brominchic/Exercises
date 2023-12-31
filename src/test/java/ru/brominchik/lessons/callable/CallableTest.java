package ru.brominchik.lessons.callable;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class CallableTest {
    private static final Logger logger = LoggerFactory.getLogger(CallableTest.class);


    @Test
    void testNormal() throws IOException, ExecutionException, InterruptedException {
        long sum = 0;
        BankWorker bankWorker = new BankWorker();
        File file = bankWorker.createFile(100, "C:\\test.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> baseList = new ArrayList<>();
        while (scanner.hasNext()) {
            int num = ((scanner.nextInt()));
            baseList.add(num);
        }
        List<Future<Long>> futures;
        try (ExecutorService service = Executors.newFixedThreadPool(5)) {
            futures = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                ArrayList<Integer> listForThread = new ArrayList<>();
                for (int j = 20 * i; j < 20 * (i + 1); j++) {
                    listForThread.add(baseList.get(j));
                }
                Future<Long> future = service.submit(new BankOperator(listForThread));
                futures.add(future);
            }
        }
        for (Future<Long> fut : futures) {
            sum += fut.get();
        }
        logger.info("Всего: " + sum);
    }
    @Test
    void testLocked() throws IOException, ExecutionException, InterruptedException {
        long sum = 0;
        ReentrantLock locker = new ReentrantLock();
        BankWorker bankWorker = new BankWorker();
        File file = bankWorker.createFile(100, "C:\\test.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> baseList = new ArrayList<>();
        while (scanner.hasNext()) {
            int num = ((scanner.nextInt()));
            baseList.add(num);
        }
        List<Future<Long>> futures;
        try (ExecutorService service = Executors.newFixedThreadPool(5)) {
            futures = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                ArrayList<Integer> listForThread = new ArrayList<>();
                for (int j = 20 * i; j < 20 * (i + 1); j++) {
                    listForThread.add(baseList.get(j));
                }
                Future<Long> future = service.submit(new BankOperatorReentrantLocked(listForThread, locker));
                futures.add(future);
            }
        }
        for (Future<Long> fut : futures) {
            sum += fut.get();
        }
        logger.info("Всего: " + sum);

    }

}
