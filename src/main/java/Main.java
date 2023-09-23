public class Main {
    public static void main(String[] args) throws InterruptedException {
        S s = new S();
        W w = new W(s);
        s.start();
        w.start();
        s.join();
        w.join();
    }
}