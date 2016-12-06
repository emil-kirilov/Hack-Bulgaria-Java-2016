package week4;

public class BinaryTree<T extends Comparable<T>> {
	private class Node {
		Node smaller = null;
		Node bigger = null;
		T value;
		
		public Node(T value){
			this.value = value;
		}
		
//		public void setSmaller(Node small) {
//			smaller = small;
//		}
//		
//		public void setBigger(Node big) {
//			bigger = big;
//		}
		
		public T getValue() {
			return value;
		}
		
		public void setValue(T value) {
			this.value = value;
		}
		
	}
	
	private Node root;

	//	public BinaryTree(T root){
	//		this.root = new Node(root);
	//	}
	
	
	private void addR(T element, Node root) {
		int comparison = root.getValue().compareTo(element);
			
		if(comparison < 0) {
			if(root.bigger == null) {
				root.bigger = new Node(element);
			} else {
				addR(element, root.bigger);
			}
		} else {
			if (root.smaller == null){
				root.smaller = new Node(element);
			} else {
				addR(element, root.smaller);
			}
		}
	}
	
	public void add(T element) {
		//TODO validate!!!
		if (root == null) {
			root = new Node(element);
		} else {
			addR(element, root);
		}
	}
	
	private void printR(Node root) {
		Node smaller = root.smaller;
		Node bigger = root.bigger;
		 
		if(smaller != null) {
			printR(smaller);
		} 
		System.out.print(root.getValue()+ "->"); 
		if (bigger != null) {
			printR(bigger);
		}
	}
	
	public void print() {
		printR(root);
		System.out.println();
	}
	
	public boolean find(T element){
		Node current = root;
		boolean result = false;
		
		while(current !=  null) {
			int comparison = current.getValue().compareTo( element );

			if (comparison < 0) {
				current = current.bigger;
			} else if (comparison > 0) {
				current = current.smaller;
			} else {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public void remove(T target){
		Node parent = getParent(target);
		Node replacee = getNode(target);
		
		if(parent.smaller == target) {
			Node replacement = findBiggestLeftChild(replacee);
			parent.smaller = replacement;
			
			replacement.bigger = replacee.bigger;
			replacee.smaller.bigger = replacement.smaller;
			replacement.smaller = replacee.smaller;
			
			//Node smallestFromReplacementTree = getSmallest(replacement);
			//smallestFromReplacementTree.smaller = replacee.smaller;
		}
		
	}
	
	private Node getParent(T target) {
		if(root == null || root.getValue() == target) {
			return null;
		}
		
		Node current = root;
		Node result = null;
		while(current !=  null) {
			int comparison = current.getValue().compareTo( target );

			if (comparison < 0) {
				//current < target
				if(current.bigger.getValue() == target) {
					result = current;
					break;
				}
				current = current.bigger;
			} else {
				//current > target
				if(current.smaller.getValue() == target) {
					result = current;
					break;
				}
				current = current.smaller;
			}
		}
		
		return result;
	}
	
	private Node getNode(T target) {
		Node current = root;
		Node result = null;
		
		while(current !=  null) {
			int comparison = current.getValue().compareTo( target );

			if (comparison < 0) {
				current = current.bigger;
			} else if (comparison > 0) {
				current = current.smaller;
			} else {
				result = current;
				break;
			}
		}
		return result;
	}
	
	private Node findBiggestLeftChild(Node parent) {
		Node current = parent.smaller;
		
		while(current.bigger != null){
			current = current.bigger;
		}
		
		return current;
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		bt.add(12);
		bt.add(10);
		bt.add(15);
		bt.add(4);
		bt.add(9);
		bt.add(17);
		bt.add(20);
		bt.add(2);
		bt.add(1);
		bt.add(7);
		bt.add(8);
		bt.add(6);
		bt.print();
		System.out.println( bt.find(1) );
		System.out.println( bt.find(33) );
		bt.remove(10);
		bt.print();
	}
}
