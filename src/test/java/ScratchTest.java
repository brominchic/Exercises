import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScratchTest {

    @Test
    public void scratchTest() {
        Scratch scratch = new Scratch();
        LinkedList linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(8);
        int[] a = {1, 2, 3, 4, 8};
        int[] b = {3, 4, 5};
        LinkedList list = Scratch.sort(a, b);
        assertTrue(list.equals(linkedList));
    }

    @Test
    void secondScratchTest() {
        Scratch scratch = new Scratch();
        LinkedList linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        int[] a = {1, 2, 3, 4, 5, 9};
        int[] b = {3, 4, 8};
        LinkedList list = Scratch.sort(a, b);
        System.out.println(list);
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

        var result = Scratch.sort(first, second);
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

        var result = Scratch.sort(first, second);
        assertEquals(100, result.size());
    }
}
