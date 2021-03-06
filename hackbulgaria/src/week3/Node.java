package week3;

public class Node<T extends Comparable<T>>{
	Node<T> right = null;
	T value;
	
	public Node(T value){
		setValue(value);
	}
	
	public void setRight(Node<T> nextEl){
		right = nextEl;
	}
	
	public T getValue(){
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	public int compareTo(Node<T> other) {
		int a = Integer.valueOf(this.toString());
		int b = Integer.valueOf(other.toString());
		if (a > b) {
			return 1;
		} else if (a == b) {
			return 0;
		}
		return -1;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}
