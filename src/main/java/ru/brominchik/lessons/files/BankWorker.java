package ru.brominchik.lessons.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface BankWorker {
    File createFile(long numOfOperations, String filePath) throws IOException;

    void doOperations(File file, long baseAccount, long finalAccount) throws FileNotFoundException;
}
