package threadsTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Threads {

	/*
	 * private class TaskT extends Thread { private Thread t = new Thread();
	 * private String tName = "dsa";
	 * 
	 * public void run() { try { for (int i = 100; i > 0; i--) {
	 * System.out.println("Thread prints " + i); // Let the thread sleep for a
	 * while. Thread.sleep(50); } } catch (InterruptedException e) { } }
	 * 
	 * public void start() { System.out.println("Starting the thread");
	 * t.start(); } }
	 */

	public static void main(String[] args) {
		PrimeFactorization prime = new PrimeFactorization(5);
		// Thread thread = new Thread(prime);
		// thread.start();
		PrimeFactorization prime2 = new PrimeFactorization(213 * 512 * 15 * 13);
		// Thread thread2 = new Thread(prime2);
		// thread2.start();
		PrimeFactorization prime3 = new PrimeFactorization(6 * 7 * 13 * 19 * 23 * 97);

		ExecutorService fixedPool = Executors.newFixedThreadPool(2);

		Future<?> result1 = fixedPool.submit(prime);
		Future<?> result2 = fixedPool.submit(prime2);
		Future<?> result3 = fixedPool.submit(prime3);

		try {
			System.out.println(result1.isDone());
			System.out.println(result2.isCancelled());
			System.out.println(result3.get());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		CallablePrimeFactorization cprime1 = new CallablePrimeFactorization(5);
		CallablePrimeFactorization cprime2 = new CallablePrimeFactorization (213 * 512 * 15 * 13);
		CallablePrimeFactorization cprime3 = new CallablePrimeFactorization (6 * 7 * 13 * 19 * 23 * 97);
		Future<String> cresult1 = fixedPool.submit(cprime1);
		Future<String> cresult2 = fixedPool.submit(cprime2);
		Future<String> cresult3 = fixedPool.submit(cprime3);

		try {
			System.out.println(cresult1.get());
			System.out.println(cresult2.get());
			System.out.println(cresult3.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		fixedPool.shutdown(); // shut down
		System.out.println("Done");
	}
}
