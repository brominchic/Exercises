package ru.brominchik.lessons.school;
public class Main {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i < 1000000; i++) {
            String binary = Integer.toBinaryString(i);
            String quaternary = Integer.toString(i, 4);
            System.out.println(quaternary);
            if (countOnes(binary) == 6&(countOnes(quaternary)==6)) {
                count++;
            }
        }
        System.out.println("Количество чисел: " + count);
    }

    public static int countOnes(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '1') {
                count++;
            }
        }
        return count;
    }
}