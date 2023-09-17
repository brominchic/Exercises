public class W extends Thread {
    private S S;
    private int i;

    public W(S s) {
        this.S = s;
    }

    @Override
    public void run() {
        this.i=30;
        while (i>= 0){
            if (S.isState()) {
                try {
                   Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
                i=i-1;
            }
        }
       S.setInterrupted(true);
        interrupt();
    }

    synchronized int getI() {
        return i;
    }
}
