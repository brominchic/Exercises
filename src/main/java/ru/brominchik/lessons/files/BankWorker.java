package ru.brominchik.lessons.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class BankWorker {
    private static final Logger logger = LoggerFactory.getLogger(BankWorker.class);
    private final Random random = new Random();
    public File file;
    private long baseAccount;
    private long finalAccount;

    public void createFile(int numOfOperations) throws IOException {
        file = new File("C:\\test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0; i < numOfOperations; i++) {
            int x = random.nextInt(200000) - 100000;
            String baseString = x + " ";
            fileOutputStream.write(baseString.getBytes());
        }
        fileOutputStream.close();
    }

    public void doOperations() throws FileNotFoundException {
        baseAccount = 1000000;
        finalAccount = 1000000;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            int sum = ((scanner.nextInt()));
            baseAccount -= sum;
            finalAccount += sum;
            if (sum < 0) {
                logger.info("Переведено со счета 2 " + sum * -1 + " На счет 1");
            } else {
                logger.info("Переведено со счета 1 " + sum + " На счет 2");
            }
        }
        System.out.println(baseAccount);
        System.out.println(finalAccount);
        scanner.close();
        boolean result = file.delete();
        if (result) {
            System.out.println("File is successfully deleted.");
        } else {
            System.out.println("File deletion failed.");
        }
    }

    public long getBaseAccount() {
        return baseAccount;
    }

    public long getFinalAccount() {
        return finalAccount;
    }
}
