package ru.brominchik.lessons.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class BankWorkerKirillImpl implements BankWorker {

    private static final Logger logger = LoggerFactory.getLogger(BankWorkerKirillImpl.class);
    private final Random random = new Random();

    @Override
    public File createFile(long numOfOperations, String filePath) throws IOException {
        File result = new File(filePath);
//        Writer writer = new BufferedWriter(new FileWriter(filePath));
        Writer writer = new FileWriter(filePath);
        for (long i = 0; i < numOfOperations; i++) {

            int x = random.nextInt(200000) - 100000;
//            logger.info("{} set to {}", i, x);
            writer.write(String.valueOf(x));
            writer.write(System.lineSeparator());

        }
        writer.close();
        return result;
    }

    @Override
    public void doOperations(File file, long baseAccount, long finalAccount) throws FileNotFoundException {

    }
}
