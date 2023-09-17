
class Scratch {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,8}; // сортированный!
        int[] b = {3,4,5}; // сортированный/
        sort(a,b);

    }
    public static int[] sort(int[] firstArr, int[] lastArr){
        var finalArr= new int[firstArr.length];
        int n = 0;
        for (int i = 0; i < firstArr.length; i++) {
            if (firstArr[i] != lastArr[n]) {
                finalArr[i]=firstArr[i];
            }
            else {
               n=n+1;
            }
        }
        return finalArr;
    }
}