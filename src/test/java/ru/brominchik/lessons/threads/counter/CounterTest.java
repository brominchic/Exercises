package ru.brominchik.lessons.threads.counter;

import org.junit.jupiter.api.Test;

public class CounterTest {
    @Test
    public void testForCorrectness() throws InterruptedException {
        StateSwitcher stateSwitcher = new StateSwitcher();
        Counter counter = new Counter(stateSwitcher);
        stateSwitcher.start();
        counter.start();
        stateSwitcher.join();
        counter.join();
    }
}
