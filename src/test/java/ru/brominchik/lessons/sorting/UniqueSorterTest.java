package ru.brominchik.lessons.sorting;

import org.junit.jupiter.api.Test;
import ru.brominchik.lessons.sorting.UniqueSorter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniqueSorterTest {

    @Test
    public void scratchTest() {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(8);
        int[] a = {1, 2, 3, 4, 8};
        int[] b = {3, 4, 5};
        List<Integer> list = UniqueSorter.sort(a, b);
        assertEquals(list, linkedList);
    }

    @Test
    void secondScratchTest() {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(9);
        int[] a = {1, 2, 3, 4, 5, 9};
        int[] b = {3, 4, 8};
        List<Integer> list = UniqueSorter.sort(a, b);
        System.out.println(list);
        assertEquals(list, linkedList);
    }

    // подумай, эффективно ли твое решение в этом случае
    @Test
    void testLeftHugeAmount() {
        int[] first = new int[105];
        for (int i = 0; i < 100; i++) {
            first[i] = i;
        }
        int[] second = new int[5];
        for (int i = 100; i < 105; i++) {
            first[i] = i;
            second[i - 100] = i;
        }

        var result = UniqueSorter.sort(first, second);
        assertEquals(100, result.size());
    }

    // подумай, эффективно ли твое решение в этом случае
    @Test
    void testRightHugeAmount() {
        int[] first = new int[105];
        int[] second = new int[5];
        for (int i = 0; i < 5; i++) {
            first[i] = i;
            second[i] = i;
        }
        for (int i = 5; i < 105; i++) {
            first[i] = i;
        }

        var result = UniqueSorter.sort(first, second);
        assertEquals(100, result.size());
    }
}
