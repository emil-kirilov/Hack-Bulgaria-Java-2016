package week4;

import week3.LinkedList;

public class QueueWith2Stacks<T extends Comparable<T>>  implements MyQueueInterface<T>{
	MyStackInterface<T> st1 = new LinkedList<T>();
	MyStackInterface<T> st2 = new LinkedList<T>();
	
	@Override
	public void enqueue(T element) {
		st1.push(element);
	}

	@Override
	public T dequeue() {
		int size = st1.getSize();
		T result = null;
		
		if (size > 0) {
			
			for (int i = 0; i < size; i++) {
				st2.push( st1.pop() );
			} 
			
			result = st2.pop();
			
			size -= 1;
			for (int i = 0; i < size; i++) {
				st1. push ( st2.pop() );
			}			
		} 
		
		return result;
	}

	@Override
	public T peek() {
		int size = st1.getSize();
		T result = null;
		
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				st2.push( st1.pop() );
			} 
			
			result = st2.peek();
			
			for (int i = 0; i < size; i++) {
				st1. push ( st2.pop() );
			}
		}
		
		return result;
	}

	@Override
	public int getSize() {
		return st1.getSize();
	}

	public static void main(String[] args) {
		QueueWith2Stacks<Integer> q = new QueueWith2Stacks<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(23);
		q.enqueue(43);
		System.out.println(q.peek());
		System.out.println(q.dequeue());
		System.out.println(q.peek());
		System.out.println(q.dequeue());
		System.out.println(q.peek());
		System.out.println(q.dequeue());
		System.out.println(q.peek());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.getSize());
	}
}

