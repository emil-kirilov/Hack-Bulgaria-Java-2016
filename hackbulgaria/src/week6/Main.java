package week6;

import java.util.Arrays;
import java.util.List;

public class Main {
	public int binarySearch(int [] arr, int target){
		int low = 0;
		int high = arr.length - 1;
			
		int indexTarget = -1;
		while(low <= high) {
		int mid = low + ((high - low) / 2);
			
		if (arr[mid] == target) {
			indexTarget = mid;
			break;
		} else if ( arr[mid] > target) {
			high = mid - 1;
		} else {
			low = mid + 1;
		}
			}
			
		return indexTarget;
	}
	
	private static class Node {
		private int value;
		private Node left;
		private Node right;
		
		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
		
//		public int getValue(){
//			return value;
//		}
		
		public static Node build(List<Integer> values) {
			if (values.isEmpty()) {
				return null;
			}
			int middle = values.size() / 2;
			int value = values.get(middle);
			Node left = build(values.subList(0, middle));
			Node right = build(values.subList(middle + 1, values.size()));
			return new Node(value, left, right);
		}
		
		public void print(String prefix) {
			if (left != null) {
				left.print(prefix + " ");
			}
			System.out.print(prefix);
			System.out.println(value);
			if (right != null) {
				right.print(prefix + " ");
			}
		}
		
		public static int size(Node node) {
			if (node == null)  {
				return 0;
		 	} 
			return 1 + size(node.left) + size(node.right);
		}

		public static int depth(Node node){
			if(node == null) {
				return 0;
			} 
			return 1 + Math.max(depth(node.left), depth(node.right));
		}		
	}
	
	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1 ,2 ,4, 6, 7, 10, 12, 15, 20, 23);
		Node root = Node.build(values);
		root.print("");
		System.out.println("-----------");
		System.out.println(Node.size(root));
		System.out.println("-----------");
		System.out.println(Node.depth(root));
		
	}
}

