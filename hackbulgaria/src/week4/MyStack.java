package week4;

import java.util.Stack;

public class MyStack<T extends Comparable<T>> implements MyStackInterface<T>{
	private class Node<T2 extends Comparable<T>>{
		Node<T2> right = null;
		T2 value;
		
		public Node(T2 value){
			setValue(value);
		}
		
		public void setRight(Node<T2> nextEl){
			right = nextEl;
		}
		
		public T2 getValue(){
			return value;
		}
		
		public void setValue(T2 value) {
			this.value = value;
		}

//		public int compareTo(Node<T> other) {
//			int a = Integer.valueOf(this.toString());
//			int b = Integer.valueOf(other.toString());
//			if (a > b) {
//				return 1;
//			} else if (a == b) {
//				return 0;
//			}
//			return -1;
//		}
		
		public String toString() {
			return String.valueOf(value);
		}
	}
	
	Node<T> head = null;
	Node<T> tail = null;
	int size = 0;
	Stack<T> sorted = new Stack<T>();
	
	/**
	 * Places the given element at the beginning of the linked list O(n)=1
	 **/
	public void addFirst(T newElement) {
		Node<T> newHead = new Node<T>(newElement);

		if (size == 0) {
			tail = newHead;
		} else {
			newHead.setRight(head);
		}
		head = newHead;

		size++;
		
		try{
			if (newElement.compareTo( sorted.peek() ) == -1) {
				sorted.push(newElement);
			} else {
				sorted.push( sorted.peek() );
			}
		} catch(Exception e) {
			sorted.push(newElement);
		}
	}
	
	public void remove(int index) {
		if (index > size - 1) {
			System.out.println("Invalid index! Max value = " + (size - 1));
		} else if (index == 0) {
			head = head.right;
			size--;
		} else {
			Node<T> current = head;

			for (int i = 0; i < index - 1; i++) {
				current = current.right;
			}
			
			if (index == size - 1) {
				current.right = null;
				tail = current;
				tail.right = null;
			} else {
				current.right = current.right.right;				
			}
			size--;
		}
		
		sorted.pop();
	}

	
	public T pop() {
		return removeHead();
	}

	private T removeHead() {
		T firstElement = null;
		
		if (size != 0){
			firstElement = head.getValue();
			remove(0);
		}
		
		return firstElement;
	}

	@Override
	public void push(T element) {
		addFirst(element);
	}

	@Override
	public T peek() {
		T firstElement = null;
		
		if (size != 0){
			firstElement = head.getValue();
		}
		
		return firstElement;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	public T getMin(){
		return sorted.peek();
	}
	
	public static void main(String[] args) {
		MyStack<Integer> test = new MyStack<Integer>();
		
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		test.push(1);
		test.pop();
		System.out.println(test.getMin());
	}
}
