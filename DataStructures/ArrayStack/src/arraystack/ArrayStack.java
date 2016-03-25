package arraystack;

import java.util.ArrayList;

public class ArrayStack<T> {

	private ArrayList<T> elements;

    public ArrayStack()
    {
        this.elements = new ArrayList<T>();
    }

    public int size() { 

    	return this.elements.size();
    }

    public void push(T element)
    {
        this.elements.add(element);
    }

    public T pop()
    {
        if (this.size() == 0)
        {
            throw new IllegalArgumentException("The stack is empty!");
        }

        T result = this.elements.remove(this.elements.size() - 1);

        return result;
    }

    public T peek()
    {
    	if (this.size() == 0)
        {
            throw new IllegalArgumentException("The stack is empty!");
        }

        T result = this.elements.get(this.elements.size() - 1);

        return result;
    }

    @SuppressWarnings("unchecked")
	public T[] ToArray()
    {
        return (T[]) this.elements.toArray();
    }

    public void Clear()
    {
        this.elements.clear();
    }

    public void TrimExcess()
    {
        this.elements.trimToSize();
    }
}
