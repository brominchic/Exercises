public class S extends Thread {
    private boolean state;
    private boolean isInterrupted;
    private W w;

    public S() {
        this.isInterrupted = false;
    }

    @Override
    public void run() {
        while (!(isInterrupted)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.state = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.state = true;
        }
        interrupt();
    }

    synchronized boolean isState() {
        return state;
    }

    public void setInterrupted(boolean interrupted) {
        isInterrupted = interrupted;
    }
}
