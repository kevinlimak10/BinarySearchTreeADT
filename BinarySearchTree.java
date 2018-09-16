import java.util.Queue;

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeADT<K, V> {
	protected Node root;

	protected class Node {
		private K key;
		private V value;
		private Node left, right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public Node next(K other) {
			return other.compareTo(key) < 0 ? left : right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public String toString() {
			return "" + key;
		}
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public V search(K key) {
		return search(root, key);
	}

	private V search(Node node, K key) {
		if (node == null) {
			return null;
		} else if (key.compareTo(node.key) == 0) {
			return node.value;
		}
		return search(node.next(key), key);
	}

	@Override
	public void insert(K key, V value) {
		root = insert(root, key, value);
	}

	private Node insert(Node node, K key, V value) {
		if (node == null) {
			return new Node(key, value);
		} else if (key.compareTo(node.key) > 0) {
			node.right = insert(node.right, key, value);
		} else if (key.compareTo(node.key) < 0) {
			node.left = insert(node.left, key, value);
		}

		return node;
	}

	@Override
	public String toString() {
		return root == null ? "[empty]" : printTree(new StringBuffer());
	}

	private String printTree(StringBuffer sb) {
		if (root.right != null) {
			printTree(root.right, true, sb, "");
		}
		sb.append(root + "\n");
		if (root.left != null) {
			printTree(root.left, false, sb, "");
		}

		return sb.toString();
	}

	private void printTree(Node node, boolean isRight, StringBuffer sb, String indent) {
		if (node.right != null) {
			printTree(node.right, true, sb, indent + (isRight ? "        " : " |      "));
		}
		sb.append(indent + (isRight ? " /" : " \\") + "----- " + node + "\n");
		if (node.left != null) {
			printTree(node.left, false, sb, indent + (isRight ? " |      " : "        "));
		}
	}

	@Override
	public boolean delete(K key) {
		return deleteByCopying(key);
	}

	private boolean deleteByCopying(K key) {
		Node parent = null, current = root;
		for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key))
			;

		if (current == null)
			return false;
		else if (current.left != null && current.right != null) {
			// Caso 3
			Node tmp = current.left;
			while (tmp.right != null)
				tmp = tmp.right;
			deleteByCopying(tmp.key);
			current.key = tmp.key;
		} else {
			// Caso 1 ou Caso 2
			Node nextNode = current.right == null ? current.left : current.right;
			if (current.equals(root))
				root = nextNode;
			else if (parent.left.equals(current))
				parent.left = nextNode;
			else
				parent.right = nextNode;
		}

		return true;
	}

	private boolean deleteByMerging(K key) {
		Node parent = null, current = root;
		for (; current != null && key.compareTo(current.key) != 0; parent = current, current = current.next(key))
			;

		if (current == null)
			return false;
		else if (current.left != null && current.right != null) {
			// Caso 3
			Node tmp = current.left;
			while (tmp.right != null)
				tmp = tmp.right;
			tmp.right = current.right;

			if (current.equals(root))
				root = current.left;
			else if (parent.left.equals(current))
				parent.left = current.left;
			else
				parent.right = current.left;
		} else {
			// Caso 1 ou Caso 2
			Node nextNode = current.right == null ? current.left : current.right;
			if (current.equals(root))
				root = nextNode;
			else if (parent.left.equals(current))
				parent.left = nextNode;
			else
				parent.right = nextNode;
		}

		return true;
	}

	@Override
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	@Override
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node + " ");
			inOrder(node.right);
		}
	}

	@Override
	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node + " ");
		}
	}

	@Override
	public void levelOrder() {
		levelOrder(root);
	}
	
	private void levelOrder(Node node) {
		if (node != null) {
			LinkedQueue<Node> level = new LinkedQueue<Node>();
			level.enqueue(node);
			Node current = null;
			while (!level.isEmpty()) {
				current = level.dequeue();
				System.out.print(current + " ");
				if (current.left != null)
					level.enqueue(current.left);
				if (current.right != null)
					level.enqueue(current.right);
			}
		}
	}

	public int countNodes() {
		if (this.isEmpty())
			return 0;
		return countNodes(this.root);
	}

	private int countNodes(Node node) {
		if (node != null) {
			return 1 + countNodes(node.left) + countNodes(node.right);
		}
		return 0;
	}

	public int countInternalNodes() {
		if (this.isEmpty())
			return 0;
		return countInternalNodes(this.root);
	}

	private int countInternalNodes(Node node) {
		if (node != null && !node.isLeaf()) {
			if (node.equals(this.root)) {
				return countInternalNodes(node.right) + countInternalNodes(node.left);
			}
			return countInternalNodes(node.right) + countInternalNodes(node.left) + 1;
		}
		return 0;
	}

	@Override
	public int countLeaves() {
		return countLeaves(this.root);
	}

	private int countLeaves(Node node) {
		if (node != null) {
			if (!node.isLeaf())
				return countLeaves(node.left) + countLeaves(node.right);
			return 1;
		}
		return 0;
	}

	@Override
	public int degree(K key) {
		if (key == null || this.isEmpty() || search(key) == null) {
			return -1;
		}
		return degree(key, root);
	}

	private int degree(K key, Node node) {
		if (key.compareTo(node.key) != 0)
			return degree(key, node.next(key));

		int degree = 0;

		if (node.right != null)
			degree += 1;
		if (node.left != null)
			degree += 1;

		return degree;
	}

	@Override
	public int degreeTree() {
		if (this.isEmpty()) {
			return -1;
		}
		return degreeTree(this.root, 0);
	}

	private int degreeTree(Node node, int treeDegree) {
		if (treeDegree == 2) {
			return treeDegree;
		}

		if (node != null) {
			int d = degree(node.key);

			if (d > treeDegree)
				treeDegree = d;

			degreeTree(node.next(node.key), treeDegree);
		}

		return treeDegree;
	}

	@Override
	public int height(K key) {
		if (key == null || this.isEmpty() || search(key) == null) {
			return -1;
		}
		return height(key, root, true);
	}

	private int height(K key, Node node, boolean find) {
		if (key.compareTo(node.key) != 0 && find)
			return height(key, node.next(key), true);

		int heightRight = 0, heightLeft = 0;

		if (node.right != null) {
			heightRight = height(key, node.right, false) + 1;
		}

		if (node.left != null) {
			heightLeft = height(key, node.left, false) + 1;
		}

		return (heightRight > heightLeft ? heightRight : heightLeft);
	}

	@Override
	public int heightTree() {
		if (this.isEmpty()) {
			return -1;
		}
		return height(this.root.key, this.root, true);
	}

	@Override
	public int depth(K key) {
		if (key == null || this.isEmpty() || search(key) == null) {
			return -1;
		}
		return depth(root, key);
	}

	private int depth(Node node, K key) {
		if (key.compareTo(node.key) == 0) {
			return 0;
		}
		return 1 + depth(node.next(key), key);
	}

	@Override
	public String ancestors(K key) {
		if (key == null || this.isEmpty() || search(key) == null)
			return null;
		return ancestors(root, key);
	}

	private String ancestors(Node node, K key) {
		if (key.compareTo(node.key) == 0)
			return node.key.toString();

		return node.key.toString() + " " + ancestors(node.next(key), key);
	}

	@Override
	public String descendents(K key) {
		if (key == null || this.isEmpty() || search(key) == null)
			return null;
		
		return descendents(key, root, true);
	}
	
	private String descendents(K key, Node node, boolean find) {		
		if (key.compareTo(node.key) != 0 && find)
			return descendents(key, node.next(key), true);
		
		String descendents = node.key.toString();
		
		if (node.right != null) {
			descendents += " " + descendents(key, node.right, false);
		}			
		
		if (node.left != null) {
			descendents += " " + descendents(key, node.left, false);
		}
		
		return descendents;
	}

}
