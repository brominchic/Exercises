package ru.brominchik.lessons.threads.feeder;

public abstract class AbstractAnimal extends Thread {

    protected final Feeder feeder;
    protected final int consumption;
    protected final String name;
    private int eatenAmount;
    private final int timeout;

    public AbstractAnimal(Feeder feeder, int consumption, String name, int timeout) {
        this.feeder = feeder;
        this.timeout = timeout;
        this.consumption = consumption;
        this.name = name;
        this.eatenAmount = 0;
    }

    @Override
    public void run() {
        while (feeder.getAmountOfFood() > 0) {
            System.out.println(name + " пытается поесть");
            this.eatenAmount += eat();
            try {
                sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println(name + " завершил работу. съедено " + this.getEatenAmount());

    }

    protected abstract int eat();

    public int getEatenAmount() {
        return eatenAmount;
    }
}
