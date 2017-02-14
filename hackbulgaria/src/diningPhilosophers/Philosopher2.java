package diningPhilosophers;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Philosopher2 implements Runnable {
    private final int id;
    private Random random = ThreadLocalRandom.current();
    private Fork lesserIdFork;
    private Fork biggerIdFork;

    public Philosopher2(int id, Fork a, Fork b) {
        this.id = id;
        this.lesserIdFork = a.getID() < b.getID() ? a : b;
        this.biggerIdFork = a.getID() < b.getID() ? b : a; 
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (Fork.pick2Forks(lesserIdFork, biggerIdFork, this)) {
                	eat();
                	lesserIdFork.putDown(this);
                	biggerIdFork.putDown(this);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WTF");
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking");
        Thread.sleep(random.nextInt(2000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating");
        Thread.sleep(random.nextInt(2000));
    }

    @Override
    public String toString() {
        return "Philosher " + id;
    }
}