package ru.brominchik.lessons.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class BankWorker {
    private static final Logger logger = LoggerFactory.getLogger(BankWorker.class);
    private final Random random = new Random();

    public File createFile(long numOfOperations, String path) throws IOException {
        File file = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        for (long i = 0; i < numOfOperations; i++) {
            int x = random.nextInt(200000) - 100000;
            String baseString = x + "\n ";
            fileOutputStream.write(baseString.getBytes());
        }
        fileOutputStream.close();
        return file;
    }

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
            logger.info("File is successfully deleted.");
        } else {
            logger.info("File deletion failed.");
        }
    }

}
