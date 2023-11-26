package ru.brominchik.lessons.school;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] graph = new int[n][n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
                if (graph[i][j] == 1) {
                    sum++;
                }
            }
        }
        int num = 0;
        ArrayList<Integer> finalList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            num = 0;
            for (int j = 0; j < n; j++) {
                if ((graph[i][j] == 1) & (j != i)) {
                    num++;
                }
                if ((graph[j][i] == 1) & (j != i)) {
                    num++;
                }
            }
            if (num == 0) {
                finalList.add(i + 1);
            }
        }
        if (finalList.size() > 0) {
            for (int i = 0; i < finalList.size(); i++) {
                System.out.print(finalList.get(i) + " ");
            }
        } else {
            System.out.println("NO");
        }
    }
}