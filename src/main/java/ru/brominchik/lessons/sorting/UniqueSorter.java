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
                if (n == lastArr.length) {
                    for (int i = j + 1; i < firstArr.length; i++) {
                        finalList.add(firstArr[i]);
                    }
                    return finalList;
                }
            } else {
                finalList.add(j);
            }
        }

        return finalList;
    }

}