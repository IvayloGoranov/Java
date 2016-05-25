package binaryTree;

import java.util.function.Consumer;

public class BinaryTree<T> {

	private T value;
	private BinaryTree<T> leftChild;
	private BinaryTree<T> rightChild;
	
	public BinaryTree(T value) {
		
        this.value = value;
    }
	
	public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
		
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getValue() {
		return this.value;
	}

	public BinaryTree<T> getLeftChild() {
		
		return this.leftChild;
	}

	public BinaryTree<T> getRightChild() {
		
		return this.rightChild;
	}

	public void printIndentedPreOrder(int indent) {
		
		//Pre-order = root node, left child, right child.
		
		StringBuilder output = new StringBuilder();		
		for (int i = 0; i < 2 * indent; i++) {
		
			output.append(" ");
		}

		System.out.print(output.toString());
        System.out.println(this.getValue());
        if (this.getLeftChild() != null) {
        	
            this.getLeftChild().printIndentedPreOrder(indent + 1);
        }

        if (this.getRightChild() != null) {
        	
            this.getRightChild().printIndentedPreOrder(indent + 1);
        }
    }

    public void eachInOrder(Consumer<T> action) {
    	
        //In-order = left child, root node, right child.
        if (this.getLeftChild() != null) {
        	
            this.getLeftChild().eachInOrder(action);
        }

        action.accept(this.getValue());

        if (this.getRightChild() != null) {
        	
            this.getRightChild().eachInOrder(action);
        }
    }

    public void eachPostOrder(Consumer<T> action) {
    	
        //Post-order = left child, right child, root node.
        if (this.getLeftChild() != null) {
        	
            this.getLeftChild().eachPostOrder(action);
        }

        if (this.getRightChild() != null) {
        	
            this.getRightChild().eachPostOrder(action);
        }

        action.accept(this.getValue());
    }
}
