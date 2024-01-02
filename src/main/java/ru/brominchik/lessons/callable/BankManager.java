package ru.brominchik.lessons.callable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class BankManager {
    public long makeOperationsFromFile(File file, int numOfThreads, Account baseAccount, Account finalAccount) throws FileNotFoundException, ExecutionException, InterruptedException {
        int sum = 0;
        List<List<Integer>> listsForThreads = readListFromFile(file, numOfThreads);//считываем файл в лист листов для каждого потока
        ThreadPoolForBankOperator service = new ThreadPoolForBankOperator(numOfThreads);//создаем тредпул
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < numOfThreads; i++) {
            Future<Long> future = service.submitBankOperator(new BankOperator(listsForThreads.get(i)));//отправляем задачи в пул
            futures.add(future);
        }
        for (Future<Long> fut : futures) {
            sum += fut.get(); //складываем значения
        }
        baseAccount.setNumOfCurrency(sum);
        finalAccount.setNumOfCurrency(sum * -1);//изменяем значения на счетах
        return sum;//возвращаем итоговое значение
    }

    public long makeOperationsFromFileWithLock(File file, int numOfThreads, Account baseAccount, Account finalAccount) throws FileNotFoundException, ExecutionException, InterruptedException {
        int sum = 0;
        ReentrantLock locker = new ReentrantLock();// создаем локер
        List<List<Integer>> listsForThreads = readListFromFile(file, numOfThreads);//считываем файл в лист листов для каждого потока
        ThreadPoolForBankOperator service = new ThreadPoolForBankOperator(numOfThreads);//создаем тредпул
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < numOfThreads; i++) {
            Future<Long> future = service.submitBankOperator(new BankOperatorReentrantLocked(listsForThreads.get(i), locker));
            futures.add(future);
        }
        for (Future<Long> fut : futures) {
            sum += fut.get();//складываем значения
        }
        baseAccount.setNumOfCurrency(sum);
        finalAccount.setNumOfCurrency(sum * -1);//изменяем значения на счетах
        return sum;//возвращаем итоговое значение
    }

    private List<List<Integer>> readListFromFile(File file, int numOfThreads) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);//создаем сканнер
        ArrayList<Integer> baseList = new ArrayList<>();// массив для хранения всех строк с файла
        while (scanner.hasNext()) {
            int num = ((scanner.nextInt()));
            baseList.add(num);//заполняем массив
        }
        for (int i = 0; i < numOfThreads; i++) {
            if (baseList.size() % numOfThreads != 0) {
                baseList.add(0); //дозаполняем массив для того чтобы потоки не дублировали работуб,для этого делаем так
                // чтобы число строк в массиве целочисленно делилось на число потоков
            }
        }
        List<List<Integer>> finalList = new ArrayList<>();
        for (int i = 0; i < numOfThreads; i++) {
            ArrayList<Integer> listForThread = new ArrayList<>();//создаем отдельный массив для каждого потока
            for (int j = baseList.size() / numOfThreads * i; j < baseList.size() / numOfThreads * (i + 1); j++) {
                listForThread.add(baseList.get(j));//заполняем массив для потока
            }
            finalList.add(listForThread);//добавляем массив для отдельного потока в общий массив массивов
        }
        return finalList;
    }
}
