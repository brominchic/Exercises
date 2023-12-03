package ru.brominchik.lessons.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class BankWorkerTimurImpl implements BankWorker {
    private static final Logger logger = LoggerFactory.getLogger(BankWorkerTimurImpl.class);
    private final Random random = new Random();

    @Override
    public File createFile(long numOfOperations, String filePath) throws IOException {
        var file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (long i = 0; i < numOfOperations; i++) {

            int x = random.nextInt(200000) - 100000;
            logger.info("{} set to {}", i, x);
            String baseString = x + " ";
            fileOutputStream.write(baseString.getBytes());
        }
        fileOutputStream.close();
        return file;
    }

    @Override
    public void doOperations(File file, long baseAccount, long finalAccount) throws FileNotFoundException {
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
}
