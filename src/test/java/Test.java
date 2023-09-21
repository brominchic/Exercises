import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {
    @org.junit.jupiter.api.Test
    void scratchTest() {
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

    @org.junit.jupiter.api.Test
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
}
