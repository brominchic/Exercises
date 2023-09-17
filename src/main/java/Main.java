public class Main {
    public static void main(String[] args) {
        S s= new S();
        W w= new W(s);
        s.start();
        w.start();

    }
}