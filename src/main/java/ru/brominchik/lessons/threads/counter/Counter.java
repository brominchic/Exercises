package ru.brominchik.lessons.threads.counter;

public class Counter extends Thread {
    private final ru.brominchik.lessons.threads.counter.StateSwitcher StateSwitcher;

    public Counter(ru.brominchik.lessons.threads.counter.StateSwitcher stateSwitcher) {
        this.StateSwitcher = stateSwitcher;
    }

    @Override
    public void run() {
        int i = 30;
        while (i >= 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (StateSwitcher.isState()) {
                System.out.println(i);
                i = i - 1;

            }
        }
        StateSwitcher.interrupt();
    }

}
