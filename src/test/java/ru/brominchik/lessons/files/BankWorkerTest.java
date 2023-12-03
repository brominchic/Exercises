package ru.brominchik.lessons.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BankWorkerTest {
    BankWorker bankWorker = new BankWorker();

    @Test
    @Timeout(2)
    public void testCreateFile() throws IOException {
        File file = bankWorker.createFile(1000_000, "C:\\test.txt");
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
        File file = bankWorker.createFile(1000, "C:\\Users\\text.txt");
        long baseAccount = 100000;
        long finalAccount = 0;
        bankWorker.doOperations(file, baseAccount, finalAccount);
        assertEquals(100000, baseAccount + finalAccount);
        assertFalse(file.exists());
    }


}
