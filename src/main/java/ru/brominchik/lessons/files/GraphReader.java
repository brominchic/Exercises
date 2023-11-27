package ru.brominchik.lessons.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.sqrt;

public class GraphReader {

    public void readMatrix(String[] arg) throws IOException {
        File file = new File("C:\\test.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            String baseString = "0 1 0 0 " + "0 0 0 0 " + "0 0 0 0 " + "0 0 0 0 "; // создаем матрицу смежности
            fileOutputStream.write(baseString.getBytes()); // записываем матрицу в файл
        }
        StringBuilder string;
        try (FileInputStream fileInputStream = new FileInputStream("C:\\test.txt")) {
            int i;
            string = new StringBuilder();
            while ((i = fileInputStream.read()) != -1) {
                string.append(((char) i));
            } // читаем файл
        }
        List<String> listOfCharsFromFile = Arrays.stream(string.toString().split(" ")).toList(); // превращаем числа и пробелы в массив знаков без пробелов
        List<Integer> integers = new ArrayList<>();
        for (String s : listOfCharsFromFile) {
            integers.add(Integer.valueOf(s));
        }// записываем массив знаков в массив цифр
        System.out.println(integers);
        int size = (int) sqrt(integers.size());
        int[][] doubleArray = new int[size][size];
        for (int j = 0; j < size; j++) { // превращаем в матрицу смежности
            for (int k = 0; k < size; k++) {
                doubleArray[j][k] = integers.get(j * size + k);
                System.out.print(doubleArray[j][k] + " ");
            }
            System.out.println();
        }

    }
}
