
public class Individual {
	static Tree tree;
	private int fitness = 0;
	
	public void generateIndividual(int maxDepth) {
		tree = new Tree(maxDepth);
	}
	
	public void printDebugTree() {
		Tree.printTree(tree.root, 0);
	}
}
