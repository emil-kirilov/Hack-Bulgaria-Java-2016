package week3;

public interface LinkedListInterface<T extends Comparable<T>> {
	public void addFirst(T newElement);
	public void addLast(T newElement);
	public void add(T newElement, int index);
	public T getFirst();
	public T getLast();
	public T get(int index);
	public int size();
	public void remove(int index);
	public void addList(LinkedListInterface<T> list);
}
