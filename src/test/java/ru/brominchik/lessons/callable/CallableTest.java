package ru.brominchik.lessons.callable;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brominchik.lessons.files.BankWorker;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallableTest {
    private static final Logger logger = LoggerFactory.getLogger(CallableTest.class);

    @Test
    void testNormal() throws IOException, ExecutionException, InterruptedException {
        BankWorker bankWorker = new BankWorker();
        Account baseAccount = new Account(0, "base");
        Account finalAccount = new Account(0, "final");
        File file = bankWorker.createFile(100, "C:\\test.txt");
        BankManager bankManager = new BankManager();
        long sum = bankManager.makeOperationsFromFile(file, 5, baseAccount, finalAccount);
        logger.info("Всего: " + sum);
        assertEquals(sum, baseAccount.getNumOfCurrency());
    }

    @Test
    void testLocked() throws IOException, ExecutionException, InterruptedException {
        BankWorker bankWorker = new BankWorker();
        Account baseAccount = new Account(0, "base");
        Account finalAccount = new Account(0, "final");
        File file = bankWorker.createFile(100, "C:\\test.txt");
        BankManager bankManager = new BankManager();
        long sum = bankManager.makeOperationsFromFileWithLock(file, 5, baseAccount, finalAccount);
        logger.info("Всего: " + sum);
        assertEquals(sum, baseAccount.getNumOfCurrency());
    }

}
