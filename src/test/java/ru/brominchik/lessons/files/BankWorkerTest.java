package ru.brominchik.lessons.files;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BankWorkerTest {
    BankWorker bankWorker = new BankWorker();

    @Test
    public void testForSomeAmount() throws IOException {
        bankWorker.createFile(1000);
        bankWorker.doOperations();

    }

    @Test
    public void testForDeletion() throws IOException {
        bankWorker.createFile(1000);
        bankWorker.doOperations();
        assertFalse(bankWorker.file.exists());
    }

    @Test
    public void testForCorrectAnswer() throws IOException {
        bankWorker.createFile(1000);
        bankWorker.doOperations();
        System.out.println(bankWorker.getBaseAccount());
        System.out.println(bankWorker.getFinalAccount());
        assertEquals(2000000, (bankWorker.getBaseAccount() + bankWorker.getFinalAccount()));
    }
}
