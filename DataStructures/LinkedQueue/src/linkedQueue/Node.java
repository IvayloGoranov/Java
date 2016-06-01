package linkedQueue;

class Node<T> {

	T value;
	Node<T> nextNode;
	
	Node(T value) {
		
        this.setValue(value);
        this.setNextNode(null);
    }

    T getValue() {
    	
    	return this.value;
	}
	
    void setValue(T value) {
		
    	this.value = value;
	}
	
    Node<T> getNextNode() {
		
    	return this.nextNode;
	}
	
    void setNextNode(Node<T> nextNode) {
		
    	this.nextNode = nextNode;
	}
}
