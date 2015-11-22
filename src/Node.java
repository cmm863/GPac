import java.util.Random;

enum NodeType { TERMINAL, FUNCTION }

class Node<T> {
	NodeType kind;
	T element;
	Node<T> left;
	Node<T> right;
	
	Node(T val) {
		this.element = val;
		this.kind = NodeType.TERMINAL;
		this.left = null;
		this.right = null;
	}
	
	Node(T o, Node<T> r, Node<T> l) {
		this.element = o;
		this.right = r;
		this.left = l;
		this.kind = NodeType.FUNCTION;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Node<?> getTerminal() {
		Random r = new Random();
		return new Node(r.nextDouble() * 10);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Node<?> getFunction() {
		Random r = new Random();
		char op;
		switch(r.nextInt(5)) {
		case 0:
			op = '+';
			break;
		case 1:
			op = '-';
			break;
		case 2:
			op = '*';
			break;
		case 3:
			op = '/';
			break;
		case 4:
			op = 'r';
			break;
		default:
			op = '?';
			break;
		}
		
		return new Node(op, null, null);
	}
	
	public static Node<?> getNode() {
		Random r = new Random();
		return r.nextBoolean() ? getFunction() : getTerminal();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Node<?> grow(int maxDepth) {
		Node root;
		
		if(maxDepth > 1) {
			root = getNode();
		} else {
			root = getTerminal();
		}
		
		if(root.kind == NodeType.FUNCTION) {
			root.left = grow(maxDepth - 1);
			root.right = grow(maxDepth - 1);
		}
		
		return root;
	}
	
	double value() {
		if(this.kind == NodeType.FUNCTION) {
			double leftVal = left.value();
			double rightVal = right.value();
			switch((char) this.element) {
			case '+':
				return leftVal + rightVal;
			case '-':
				return leftVal - rightVal;
			case '*':
				return leftVal * rightVal;
			case '/':
				return leftVal / rightVal;
			case 'r':
				Random r = new Random();
				try {
					return r.nextInt((int) Math.abs(leftVal-rightVal)) + leftVal;
				} catch (IllegalArgumentException e) {
					return leftVal;
				}
			default:
				break;
			}
		} else {
			return Double.parseDouble((String) String.valueOf(this.element));
		}
		return 0;
	}
	
}