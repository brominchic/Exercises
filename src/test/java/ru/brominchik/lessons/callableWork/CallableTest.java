package ru.brominchik.lessons.callableWork;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brominchik.lessons.callable_work.BankOperator;
import ru.brominchik.lessons.callable_work.BankOperatorReentrantLocked;
import ru.brominchik.lessons.callable_work.ConditionalCollection;
import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallableTest {
    private static final Logger logger = LoggerFactory.getLogger(CallableTest.class);
    @Test
    void testNormal() throws IOException, ExecutionException, InterruptedException {
        long sum = 0;
        ExecutorService service = Executors.newFixedThreadPool(5);
        List<Future<Long>> futures = new ArrayList<>();
        BankWorker bankWorker = new BankWorker();
        File file = bankWorker.createFile(100, "C:\\test.txt");
        for (int i = 0; i < 5; i++) {
            Future<Long> future = service.submit(new BankOperator(file, 20));
            futures.add(future);
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
        ExecutorService service = Executors.newFixedThreadPool(5);
        BankWorker bankWorker = new BankWorker();
        File file = bankWorker.createFile(100, "C:\\test.txt");
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<Long> future = service.submit(new BankOperatorReentrantLocked(file, 20, locker, i, 5));
            futures.add(future);
        }

        for (Future<Long> fut : futures) {
            sum += fut.get();
        }

        logger.info("Всего: " + sum);
    }

    @Test
    void testAddCollection() {
        ConditionalCollection<String> conditionalCollection = new ConditionalCollection();
        conditionalCollection.add("abaerere3y6746776477878yyuyyyyy");
        assertTrue(conditionalCollection.isEmpty());
        conditionalCollection.add("abaerere3y6746776477878");
        assertEquals(1, conditionalCollection.size());
    }
}
