public class Main {
    public static void main(String[] args) throws InterruptedException {
        StateSwitcher stateSwitcher = new StateSwitcher();
        Counter counter = new Counter(stateSwitcher);
        stateSwitcher.start();
        counter.start();
        stateSwitcher.join();
        counter.join();
    }
}