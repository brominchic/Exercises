package ru.brominchik.lessons.files;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BankWorkerTest {
    BankWorker bankWorker=new BankWorker();
    MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @Test
    @Timeout(120)
    public void testCreateFile() throws IOException {
        File file = bankWorker.createFile(1_000_000_000, "C:\\test.txt");
        Scanner scanner = new Scanner(file);
        long numOfStrings = 0;
        while (scanner.hasNext()) {
            int sum = ((scanner.nextInt()));
            numOfStrings++;
            assertTrue((sum <= 100000) & (sum >= -100000));
        }
        assertEquals(1000_000, numOfStrings);
    }

    @Test
    public void testDoOperations() throws IOException {
        File file = bankWorker.createFile(10000, "C:\\Users\\text.txt");
        long baseAccount = 100000;
        long finalAccount = 0;
        bankWorker.doOperations(file, baseAccount, finalAccount);
        assertEquals(100000, baseAccount + finalAccount);
        assertFalse(file.exists());
    }
    @BeforeEach
    @AfterEach
    public void logAmts() {
        System.out.printf("Initial memory: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getInit() / 1073741824);
        System.out.printf("Used heap memory: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getUsed() / 1073741824);
        System.out.printf("Max heap memory: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getMax() / 1073741824);
        System.out.printf("Committed memory: %.2f GB%n", (double) memoryMXBean.getHeapMemoryUsage().getCommitted() / 1073741824);
    }

}
