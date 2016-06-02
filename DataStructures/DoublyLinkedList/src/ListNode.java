
class ListNode<T> {

	private T value;
	private ListNode<T> nextNode;
	private ListNode<T> previousNode; 
	
	ListNode(T value) {
		
		this.value = value;
    }

	public ListNode<T> getNextNode() {
		
		return this.nextNode;
	}

	void setNextNode(ListNode<T> nextNode) {
		
		this.nextNode = nextNode;
	}

	public ListNode<T> getPreviousNode() {
		
		return this.previousNode;
	}

	void setPreviousNode(ListNode<T> previousNode) {
		
		this.previousNode = previousNode;
	}

	public T getValue() {
		
		return this.value;
	}   
}
