import java.util.LinkedList;

class Scratch {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 8}; // сортированный!
        int[] b = {3, 4, 5}; // сортированный/
        System.out.println(sort(a, b));
    }

    public static LinkedList sort(int[] firstArr, int[] lastArr) {
        var finalList = new LinkedList<>();
        int n = 0;
        for (int i = 0; i < firstArr.length; i++) {
            if (firstArr[i] == lastArr[n]) {
                n = n + 1;
            } else {
                finalList.add(firstArr[i]);
            }
        }
        return finalList;
    }

}