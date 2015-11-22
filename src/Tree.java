
public class Tree {
	Node<?> root;
	int max_depth;
	int depth;
	
	Tree(int maxDepth) {
		this.max_depth = maxDepth;
		this.root = Node.grow(this.max_depth);
		while(getDepth(this.root) == 1) {
			this.root = Node.grow(this.max_depth);
		}
		this.depth = getDepth(this.root);
	}
	
	Tree(Node<?> r, int maxDepth) {
		this.root = r;
		this.max_depth = maxDepth;
		this.depth = getDepth(this.root);
	}
	
	int getDepth(Node<?> n) {
		if(n == null) {
			return 0;
		}
		int leftVal = getDepth(n.left);
		int rightVal = getDepth(n.right);
		
		int x = leftVal > rightVal ? leftVal + 1 : rightVal + 1;
		return x;
	}
	
	public static void printTree(Node<?> root, int level) {
		if(root == null) {
			return;
		}
		printTree(root.right, level + 1);
		if(level != 0) {
			for(int i = 0; i < level-1; i++) 
				System.out.print("|\t");
				System.out.println("|-------" + root.element);
		} else {
			System.out.println(root.element);
		}
		printTree(root.left, level + 1);
	}
	public double getValue() {
		return root.value();
	}
}
