package ru.brominchik.lessons.files;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BankWorkerTest {

    static final String FILE_PATH = "C:\\Users\\admin\\test.txt";

    BankWorker bankWorker = new BankWorkerTimurImpl();

    @Test
    public void testForSomeAmount() throws IOException {
        var file = bankWorker.createFile(1000L, FILE_PATH);
//        bankWorker.doOperations(file);

    }

    @Test
    public void testForDeletion() throws IOException {
        var createdFile = bankWorker.createFile(1000, FILE_PATH);
        bankWorker.doOperations(createdFile, 100000L, 0);
        assertFalse(createdFile.exists());
    }

    @Test
    public void testForCorrectAnswer() throws IOException {

        var file = bankWorker.createFile(1000L, FILE_PATH);
        long sum = 1000000L;
        long acc1 = sum;
        long acc2 = 0;
        bankWorker.doOperations(file, acc1, acc2);
        assertEquals(sum, acc1 + acc2);
    }
}
