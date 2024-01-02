package ru.brominchik.lessons.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankWorker {
    private static final Logger logger = LoggerFactory.getLogger(BankWorker.class);

    private final Random random = new Random();

    public File createFile(long numOfOperations, String path) throws IOException {
        File file = new File(path);
        OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        for (long i = 0; i < numOfOperations; i++) {
            int x = random.nextInt(200000) - 100000;
            String baseString = x + "\n ";
            fileOutputStream.write(baseString.getBytes());
        }
        fileOutputStream.close();
        return file;
    }

    public long doOperationsFromFile(File file, long baseAccount, long finalAccount) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            int sum = ((scanner.nextInt()));
            baseAccount -= sum;
            finalAccount += sum;
            if (sum < 0) {
                logger.info("Переведено со счета 2 {} На счет 1", sum * -1);
            } else {
                logger.info("Переведено со счета 1 {}  счет 2", sum);
            }
        }
        scanner.close();
        return baseAccount;
    }

    public long doOperationsFromList(List<Integer> list) {
        long baseAccount = 0;
        for (int i = 0; i < list.size(); i++) {
            int sum = (list.get(i));
            baseAccount += sum;
            if (sum < 0) {
                logger.info("Переведено со счета 2 {} На счет 1", sum * -1);
            } else if (sum > 0) {
                logger.info("Переведено со счета 1 {}  счет 2", sum);
            }
            i++;//идем по полученному массиву и меняем значение счета
        }
        return baseAccount;
    }

}
