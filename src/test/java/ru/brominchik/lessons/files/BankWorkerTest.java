package ru.brominchik.lessons.files;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BankWorkerTest {

    static final String FILE_PATH = "C:\\Users\\admin\\test.txt";

    BankWorker bankWorker = new BankWorkerTimurImpl();
    BankWorker bankWorkerKirill = new BankWorkerKirillImpl();
    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @BeforeEach
    @AfterEach
    void setUp() {
        logAmts();
    }

    @Test
    public void testForSomeAmount() throws IOException {

        var file = bankWorker.createFile(1_000_000L, FILE_PATH);
        file.delete();
//        bankWorker.doOperations(file);

    }

    @Test
    public void testForSomeAmountKirill() throws IOException {

        var file = bankWorkerKirill.createFile(1_000_000L, FILE_PATH);
        file.delete();
//        bankWorker.doOperations(file);

    }

    @Test
    @Disabled
    public void testForDeletion() throws IOException {
        var createdFile = bankWorker.createFile(1000, FILE_PATH);
        bankWorker.doOperations(createdFile, 100000L, 0);
        assertFalse(createdFile.exists());
    }

    @Test
    @Disabled
    public void testForCorrectAnswer() throws IOException {

        var file = bankWorker.createFile(1000L, FILE_PATH);
        long sum = 1000000L;
        long acc1 = sum;
        long acc2 = 0;
        bankWorker.doOperations(file, acc1, acc2);
        assertEquals(sum, acc1 + acc2);
    }

    private void logAmts() {
        System.out.println(String.format("Initial memory: %.2f GB",
                (double) memoryMXBean.getHeapMemoryUsage().getInit() / 1073741824));
        System.out.println(String.format("Used heap memory: %.2f GB",
                (double) memoryMXBean.getHeapMemoryUsage().getUsed() / 1073741824));
        System.out.println(String.format("Max heap memory: %.2f GB",
                (double) memoryMXBean.getHeapMemoryUsage().getMax() / 1073741824));
        System.out.println(String.format("Committed memory: %.2f GB",
                (double) memoryMXBean.getHeapMemoryUsage().getCommitted() / 1073741824));
    }
}
