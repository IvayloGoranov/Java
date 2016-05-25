package binaryTree;

public class PlayWithTrees {

	public static void main(String[] args) {
		
		Tree<Integer> tree =
	            new Tree<Integer>(7,
	                new Tree<Integer>(19,
	                    new Tree<Integer>(1),
	                    new Tree<Integer>(12),
	                    new Tree<Integer>(31)),
	                new Tree<Integer>(21),
	                new Tree<Integer>(14,
	                    new Tree<Integer>(23),
	                    new Tree<Integer>(6)));

	        System.out.println("Tree (indented):");
	        tree.print(0);

	        System.out.println("Tree nodes:");
	        tree.each(c -> System.out.print(" " + c));
	        System.out.println();
	        
	        System.out.println();

	        BinaryTree<String> binaryTree =
	            new BinaryTree<String>("*",
	                new BinaryTree<String>("+",
	                    new BinaryTree<String>("3"),
	                    new BinaryTree<String>("2")),
	                new BinaryTree<String>("-",
	                    new BinaryTree<String>("9"),
	                    new BinaryTree<String>("6")));

	        System.out.println("Binary tree (indented, pre-order):");
	        binaryTree.printIndentedPreOrder(0);

	        System.out.println("Binary tree nodes (in-order):");
	        binaryTree.eachInOrder(c -> System.out.print(" " + c));
	        System.out.println();

	        System.out.print("Binary tree nodes (post-order):");
	        binaryTree.eachPostOrder(c -> System.out.print(" " + c));
	        System.out.println();
	}
}
