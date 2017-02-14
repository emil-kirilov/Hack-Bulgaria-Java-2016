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
	
	public int getID() {
		return id;
	}

	//Method for class Philosopher 2
	public static boolean pick2Forks(Fork leserID, Fork biggerID, Philosopher2 p) {
		boolean result = false;
		if (leserID.lock.tryLock()){
			System.out.println(p + " picked up " + leserID);
			
			if (biggerID.lock.tryLock()) {
				System.out.println(p + " picked up " + biggerID);
				result = true;
			} else {
				leserID.lock.unlock();
				System.out.println(p + " put down " + leserID);
			}
		}
		return result;
	}
	
	public void putDown(Philosopher2 p) {
		if (this != null) {
			lock.unlock();
			System.out.println(p + " put down " + this);
		}
	}
}