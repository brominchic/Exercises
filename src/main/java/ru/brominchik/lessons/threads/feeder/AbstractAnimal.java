package ru.brominchik.lessons.threads.feeder;

public abstract class AbstractAnimal extends Thread {

    protected final Feeder feeder;
    protected final int consumption;
    protected final String name;
    protected boolean isAlive;
    private int eatenAmount;

    public AbstractAnimal(Feeder feeder, int consumption, String name) {
        this.feeder = feeder;
        this.isAlive = true;
        this.consumption = consumption;
        this.name = name;
        this.eatenAmount = 0;
    }

    @Override
    public void run() {
        while (this.isAlive) {
            System.out.println(name + " пытается поесть");
            this.eatenAmount += eat();
            try {
                sleep(300);
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
