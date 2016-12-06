package week6;

public class MyMap<K, V> implements MapInterface<K, V>{
	BucketInterface<K, V>[] buckets;
	private double loadFactor;
	private int size;
	
	public MyMap() {
		size = 8;
		loadFactor = 5; 
		buckets = new MyLinkedBucket[size];
	}
	
	
	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
