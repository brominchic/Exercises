package ru.brominchik.lessons.threads;

public class Counter extends Thread {
    private final ru.brominchik.lessons.threads.StateSwitcher StateSwitcher;

    public Counter(StateSwitcher stateSwitcher) {
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
