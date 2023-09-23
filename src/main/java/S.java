public class S extends Thread {
    private boolean state;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
            this.state = !(this.state);
            System.out.println(this.state);
        }
    }

    synchronized boolean isState() {
        return state;
    }

}
