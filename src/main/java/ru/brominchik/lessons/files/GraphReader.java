package ru.brominchik.lessons.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphReader {

    public static void main(String[] arg) throws IOException {
        File file = new File("C:\\test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        String array = "0 1 1 0 " + "0 0 0 1 " + "0 0 0 1 " + "0 0 0 0 ";

        fileOutputStream.write(array.getBytes());
        FileInputStream fileInputStream = new FileInputStream("C:\\test.txt");
        int i;
        StringBuilder string = new StringBuilder();
        while ((i = fileInputStream.read()) != -1) {

            string.append(((char) i));
        }
        List<String> strings = Arrays.stream(string.toString().split(" ")).toList();
        List<Integer> integers = new ArrayList<>();
        for (String s : strings) {
            integers.add(Integer.valueOf(s));
        }
        int length = (int) Math.sqrt(integers.size());
        int[][] twoArray = new int[length][length];
        for (int j = 0; j < length; j++) {
            for (int k = 0; k < length; k++) {
                twoArray[j][k] = integers.get(j + k);
            }
        }
        for (int j = 0; j < length; j++) {
            for (int k = 0; k < length; k++) {
                System.out.print(twoArray[j][k] + " ");
            }
            System.out.println("");
        }
    }
}
