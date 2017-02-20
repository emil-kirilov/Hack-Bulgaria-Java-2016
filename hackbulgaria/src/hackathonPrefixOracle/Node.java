package hackathonPrefixOracle;

public abstract class Node {
	public boolean isInformationNode() {
		return getClass().getSimpleName().equals("InformationNode");		
	}
	
	public static InformationNode asInformationNode(Node node) {
		return (InformationNode) node;
	}
	
	public static BranchNode asBranchNode(Node node) {
		return (BranchNode) node;
	}
}
