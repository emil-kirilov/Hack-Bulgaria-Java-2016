package diningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
	private final int id;
	private Lock lock = new ReentrantLock();

	public Fork(int id) {
		this.id = id;
	}

	public synchronized static boolean pick2Forks(Fork leftFork, Fork rightFork, Philosopher p) {
		boolean result = false;
		if (leftFork.lock.tryLock()){
			System.out.println(p + " picked up " + leftFork);
			
			if (rightFork.lock.tryLock()) {
				System.out.println(p + " picked up " + rightFork);
				result = true;
			} else {
				leftFork.lock.unlock();
				System.out.println(p + " put down " + leftFork);
			}
		}
		return result;
	}

	public void putDown(Philosopher p) {
		if (this != null) {
			lock.unlock();
			System.out.println(p + " put down " + this);
		}
	}

	@Override
	public String toString() {
		return "Fork " + id;
	}
}