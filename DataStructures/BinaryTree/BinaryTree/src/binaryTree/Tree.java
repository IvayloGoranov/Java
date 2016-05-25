package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Tree<T> {

	private T value;
	private List<Tree<T>> children;
	
	public Tree(T value, Tree<T>... children) {
		
        this.value = value;
        this.children = new ArrayList<Tree<T>>();
        for (Tree<T> child : children) {
        	
            this.children.add(child);
        }
    }

    public T getValue() {
		
    	return this.value;
	}

	public List<Tree<T>> getChildren() {
		
		return this.children;
	}

	public void print(int indent) {
		
		StringBuilder output = new StringBuilder();		
		for (int i = 0; i < 2 * indent; i++) {
		
			output.append(" ");
		}

		System.out.print(output.toString());
		System.out.println(this.getValue());
        for (Tree<T> child : this.getChildren()) {
        	
            child.print(indent + 1);
        }
    }

    public void each(Consumer<T> action) {
    	
        action.accept(this.getValue());
        for (Tree<T> child : this.getChildren())
        {
            child.each(action);
        }
    }
}
