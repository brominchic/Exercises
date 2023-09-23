public class W extends Thread {
    private final S S;
    private int i;

    public W(S s) {
        this.S = s;
    }

    @Override
    public void run() {
        this.i = 30;
        while (i >= 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (S.isState()) {
                System.out.println(i);
                i = i - 1;

            }
        }
        S.interrupt();
    }

}
