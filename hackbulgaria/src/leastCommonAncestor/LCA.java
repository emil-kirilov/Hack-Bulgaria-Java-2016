package leastCommonAncestor;

public class LCA {

	public Node getLCA(Node x, Node y) {
		int lengthChainX = 0;
		int lengthChainY = 0;
		
		Node currentX = x;
		while(currentX.parent != null) {
			lengthChainX++;
			currentX = currentX.parent;
		}
		
		Node currentY = y;
		while(currentY.parent != null) {
			lengthChainY++;
			currentY = currentY.parent;
		}
		
		/*
		 * Up to this moment I have found the length of the two ancestor chains.
		 * Ancestor chains will have at least one common element - the root. If it is deeper than the root
		 * the two chains will split at a maximum depth of either x's or y's - whichever is shallower in the tree.
		 * I will move up on the longer ancestor chain to the first level of the tree that could contain a common element -
		 * that is either x or y - as they are last in their ancestor chain they are potentially the LCA.
		 * From that point on I will move up the ancestor chains simultaneously and check whether they join at that level
		 */
		
		//System.out.println(lengthChainX);
		//System.out.println(lengthChainY);

		currentX = x;
		currentY = y;
		if (lengthChainX > lengthChainY) {
			for (int i = 0; i < lengthChainX - lengthChainY; i++) {
				currentX = currentX.parent; 
			}
		} else {
			for (int i = 0; i < lengthChainY - lengthChainX; i++) {
				currentY = currentY.parent; 
			}	
		}
		
		//System.out.println(currentX.name);
		//System.out.println(currentY.name);
		
		Node LCA = currentX;
		while (currentX != currentY) {
			currentX = currentX.parent;
			currentY = currentY.parent;
			LCA = currentX;
		}
		
		return LCA;
	}
	
}
