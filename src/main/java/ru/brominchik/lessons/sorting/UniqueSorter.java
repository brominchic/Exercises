package ru.brominchik.lessons.sorting;

import java.util.LinkedList;
import java.util.List;

class UniqueSorter {

    public static List<Integer> sort(int[] firstArr, int[] lastArr) {
        List<Integer> finalList = new LinkedList<>();
        int n = 0;
        for (int j : firstArr) {
            if (j == lastArr[n]) {
                n = n + 1;
            } else {
                finalList.add(j);
            }
        }

        return finalList;
    }

}