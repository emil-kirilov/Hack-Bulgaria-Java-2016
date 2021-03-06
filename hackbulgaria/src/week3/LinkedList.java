package week3;

import java.util.ArrayList;

import week4.MyQueueInterface;
import week4.MyStackInterface;

public class LinkedList<T extends Comparable<T> > implements LinkedListInterface<T>, MyQueueInterface<T>, MyStackInterface<T>{
	Node<T> head = null;
	Node<T> tail = null;
	int size = 0;

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
	}

	/**
	 * Places the given element at the end of the linked list 
	 * O(n) = 1
	 */
	public void addLast(T newElement) {
		Node<T> newTail = new Node<T>(newElement);

		if (size == 0) {
			head = newTail;
		} else {
			tail.right = newTail;
		}
		tail = newTail;

		size++;
	}

	/**
	 * Places the given element at the given index Counting starts from 0
	 * Example: add(5,2) ---> 5 will be the 3rd element in the linked list
	 * add(5,0) ---> 5 will be the 1st element(head) in the linked list 
	 * O(n) = n
	 */
	public void add(T newElement, int index) {
		if (index > size) {
			System.out.println("Invalid index! Max value = " + size);
		} else if (index == 0) {
			addFirst(newElement);
		} else if (index == size) {
			addLast(newElement);
		} else {
			Node<T> newEl = new Node<T>(newElement);
			Node<T> current = head;

			int counter = 1;
			while (counter != index) {
				current = current.right;
				counter++;
			}

			newEl.right = current.right;
			current.right = newEl;
			size++;
		}
	}

	/**
	 * Returns the head 
	 * O(n) = 1
	 */
	public T getFirst() {
		if (size != 0) {
			return head.getValue();
		}
		return null;
	}

	/**
	 * Returns the tail 
	 * O(n) = 1
	 */
	public T getLast() {
		if (size != 0) {
			return tail.getValue();
		}
		return null;
	}

	/**
	 * Return the element at the given index if such index exists
	 * O(n) = n
	 */
	public T get(int index) {
		if (index > size - 1) {
			return null;
		} else if (index == 0) {
			return head.getValue();
		} else if (index == size - 1) {
			return tail.getValue();
		} else {

			Node<T> current = head;

			int counter = 0;
			while (counter != index) {
				current = current.right;
				counter++;
			}

			return current.getValue();
		}
	}

	/**
	 * Returns the size of the linked list
	 * O(n) = 1
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes the element at the given index if such index exist
	 * Index count starts from 0
	 * O(n) = n
	 */
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
	}

	/**
	 * Concatenates the given linked
	 * O(n) = n  //the given list's length
	 */
	public void addList(LinkedListInterface<T> list) {
		for (int i = 0; i < list.size(); i++) {
			Node<T> current = new Node<T>( list.get(i) );
			tail.right = current;
			tail = current;
			size++;
		}
	}

	/**
	 * Print the linked list in format   o->o->o->null
	 * O(n) = n
	 */
	public String toString() {
		if (size == 0) {
			return "empty";
		}

		String result = "";

		Node<T> current = head;
		while (current != null) {
			result += current.value + "->";
			current = current.right;
		}

		return result + "null";
	}
	
	/**
	 * Return the k-th element to the last if such index exist
	 * O(n) = n
	 */
	public T kthToLast(int index) {
		if(index > size - 1){
			return null;
		} else if (index == 0){
			return getLast();
		} else if (index == size - 1) {
			return getFirst();
		} else {
			Node<T> iterator = head;
			
			for (int i = 0; i < index; i++) {
				iterator = iterator.right;
			}
			
			Node<T> followUp = head;
			
			while(iterator.right != null){
				iterator = iterator.right;
				followUp = followUp.right;
			}
				
			return followUp.getValue();
		}
	}
	
	/**
	 * Instead of deleting the element at the given index (if it exist)
	 * we change its value and right pointer to copy those of the next
	 * element in the linked list 
	 * O(n) = n (usage of getNode)
	 */
	public void fastRemove(int index){
		if (index > size - 1) {
			System.out.println("Invalid index! Max value = " + (size - 1) );
		} else if (index == 0) {
			remove(0);
		} else if (index == size - 1){
			Node<T> change = getNode(index - 1);
			change.right = null;
			tail = change;
		} else {
			Node<T> change = getNode(index);
			if (index == size - 2) {
				tail = change;
			}		
			change.setValue( change.right.getValue() );
			change.right = change.right.right;
			size--;
		}
	}
	
	/**
	 * Return the node at the given index if such index exists
	 * O(n) = n
	 */
	private Node<T> getNode(int index){
		if (index > size - 1) {
			return null;
		} else if (index == 0) {
			return head;
		} else if (index == size - 1) {
			return tail;
		} else {

			Node<T> current = head;

			int counter = 0;
			while (counter != index) {
				current = current.right;
				counter++;
			}

			return current;
		}
	}
	
	/**
	 *  Partitions a linked list around a value x, 
	 *  such that all nodes less than x come before 
	 *  all nodes greater than or equal to x.
	 *  O(n) = n
	 */
	public LinkedList<T> partition(T value) {
		LinkedList<T> lower = new LinkedList<T>();
		LinkedList<T> upper = new LinkedList<T>();
		Node<T> other = new Node<T>(value);
		
		for (int i = 0; i < size; i++) {
			Node<T> current = getNode(i);
			if (current.compareTo(other) == -1) {
				lower.addLast(current.getValue());
			} else if (current.compareTo(other) == 1) {
				upper.addLast(current.getValue());
			}
		}
		
		lower.addLast(value);
		lower.addList(upper);
		return lower;
	}

	/**
	 * Finds the first common elements between 2 LinkedLists
	 * and print its value.
	 *  O(n) = n
	 */
	public T getFirstInLoop(Node<T> begining){
		Node<T> slowRunner = begining;
		Node<T> fastRunner = begining;
		
		// TODO validate
		fastRunner = begining.right.right;
		slowRunner = begining.right;
		
		while(fastRunner != slowRunner){
			fastRunner = begining.right.right;
			slowRunner = begining.right;
		}
		
		fastRunner = begining;
		
		while(fastRunner != slowRunner){
			fastRunner = begining.right;
			slowRunner = begining.right;
		}
		
		return fastRunner.getValue();
	}
	
	/**
	 * Checks if there is a loop in the LinkedList.
	 *  O(n) = n
	 */
	public boolean isThereLoop(){
		Node<T> slowRunner = head;
		Node<T> fastRunner = head;
		
		fastRunner = head.right.right;
		slowRunner = head.right;
		
		while(fastRunner != slowRunner && (fastRunner.right.right != null)){
			fastRunner = fastRunner.right.right;
			slowRunner = slowRunner.right;
		}
			
		if (fastRunner == slowRunner){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the LinkedList is a palindrom.
	 *  O(n) = n
	 */
	public boolean isPalindrom(){
		ArrayList<T> firstHalf = new ArrayList<T>();
		Node<T> current = head;
		for (int i = 0; i < size / 2; i++) {
			firstHalf.add( current.getValue() );
			current = current.right;
		}
		
		if (size % 2 == 1) {
			current = current.right;
		}
		
		for (int i = firstHalf.size() - 1; i > 0; i--) {
			if (current.getValue() != firstHalf.get(i)) {
				return false;
			}
		}
			
		return true;
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> test = new LinkedList<Integer>();
		test.addFirst(2);
		test.addFirst(1);
		test.addLast(3);
		test.addLast(4);
		test.add(5, 2);
		test.add(0, 0);
		test.add(10, 6);
		System.out.println(test);
		System.out.println(test.getFirst());
		System.out.println(test.getLast());
		System.out.println(test.get(0));
		System.out.println(test.get(3));
		System.out.println(test.get(6));
		System.out.println(test.size());
		test.remove(0);
		System.out.println(test);
		System.out.println(test.size());
		test.remove(2);
		System.out.println(test);
		System.out.println(test.size());
		test.remove(4);
		System.out.println(test);
		System.out.println(test.size());
		
		LinkedList<Integer> test2 = new LinkedList<Integer>();
		test2.addFirst(5);
		test2.addLast(6);
		test2.addLast(7);	
		test2.addLast(8);
		System.out.println(test2);
		System.out.println(test2.get(0));
		System.out.println(test2.get(1));
		System.out.println(test2.get(2));
		System.out.println(test2.get(3));
		test.addList(test2);
		System.out.println(test);
		System.out.println(test.kthToLast(7));
		System.out.println(test.get(1));
		System.out.println(test);
		test.fastRemove(2);
		System.out.println(test);
		test.fastRemove(0);
		System.out.println(test);
		test.fastRemove(5);
		System.out.println(test);
		
		LinkedList<Integer> test3 = new LinkedList<Integer>();
		test3.addFirst(1);
		test3.addLast(5);
		test3.addLast(2);	
		test3.addLast(3);
		test3.addLast(0);
		System.out.println(test3);
		test3 = test3.partition(2);
		System.out.println(test3);
		System.out.println(test3.isPalindrom());
		
		LinkedList<Integer> test4 = new LinkedList<Integer>();
		test4.addFirst(1);
		test4.addLast(2);
		test4.addLast(3);	
		test4.addLast(2);
		test4.addLast(1);
		System.out.println(test4.isPalindrom());
	}

	@Override
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
	
	@Override
	public void enqueue(T element) {
		addLast(element);
	}

	@Override
	public T dequeue() {
		return removeHead();
	}
}
