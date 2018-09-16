
public class ADTtest {

	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> bi = new BinarySearchTree<>();
		for(int i=0;i<10;i++) {
		bi.insert(i,i);
		}
		for(int j=-1;j>-10;j--)
			bi.insert(j, j);
		System.out.println(bi);
		System.out.println(bi.countLeaves());
		System.out.println(bi.countNodes());
		System.out.println("Nodes: " + bi.countNodes());
		System.out.println("Internal nodes:" + bi.countInternalNodes());
		System.out.println("Leaves: " + bi.countLeaves());
		System.out.println("Degree: " + bi.degree(-5));
		System.out.println("Degree tree: " + bi.degreeTree());
		System.out.println("Height: " + bi.height(6));
		System.out.println("Height tree: " + bi.heightTree());
		System.out.println("Depth: " + bi.depth(5));
		System.out.println("Ancestors: " + bi.ancestors(11));
		System.out.println("Descendents: " + bi.descendents(7));
		
		BinarySearchTree<Integer, Integer> tri = new BinarySearchTree<>();
		System.out.println(tri.countLeaves());
		System.out.println(tri.countNodes());
		System.out.println("Nodes: " + tri.countNodes());
		System.out.println("Internal nodes:" + tri.countInternalNodes());
		System.out.println("Leaves: " + tri.countLeaves());
		System.out.println("Degree: " + tri.degree(100));
		System.out.println("Degree tree: " + tri.degreeTree());
		System.out.println("Height: " + tri.height(6));
		System.out.println("Height tree: " + tri.heightTree());
		System.out.println("Depth: " + tri.depth(5));
		System.out.println("Ancestors: " + tri.ancestors(11));
		System.out.println("Descendents: " + tri.descendents(9));
		
		BinarySearchTree<Integer, Integer> comp = new BinarySearchTree<>();
		
		comp.insert(4, 4);
		comp.insert(10, 10);
		comp.insert(2, 2);
		comp.insert(1, 1);
		comp.insert(11,11);
		comp.insert(3,3);
		comp.insert(8, 8);
		
		System.out.println(comp);
		System.out.println(comp.countNodes());
		System.out.println(comp.degree(12));
		System.out.println(comp.countLeaves());
		System.out.println(comp.countInternalNodes());
		
		
		
		BinarySearchTreeADT<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
		
		int[] elemUs = null;
		
		boolean balanced = true;
		
		if (balanced) {
			int[] elem = {
					4,6,2,5,1,7,3,9,11,13,15,14,12,10,16,20,22,17,8
			};
			elemUs = elem;
		}else {
			int[] elem = {
					1,2,3,4,5,6,7
			};
			elemUs = elem;
		}
		
		for (int i = 0; i < elemUs.length; i++) {
			tree.insert(elemUs[i], elemUs[i]);
		}
		System.out.println(tree);
		System.out.println("Nodes: " + tree.countNodes());
		System.out.println("Internal nodes:" + tree.countInternalNodes());
		System.out.println("Leaves: " + tree.countLeaves());
		System.out.println("Degree: " + tree.degree(100));
		System.out.println("Degree tree: " + tree.degreeTree());
		System.out.println("Height: " + tree.height(6));
		System.out.println("Height tree: " + tree.heightTree());
		System.out.println("Depth: " + tree.depth(5));
		System.out.println("Ancestors: " + tree.ancestors(11));
		System.out.println("Descendents: " + tree.descendents(7));
		
		
		
		tree.levelOrder();
		
	}
}


