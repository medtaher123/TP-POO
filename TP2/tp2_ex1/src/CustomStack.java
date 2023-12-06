public class CustomStack<T> {

	private int capacity;
	private Object[] stackArray;
	private int top = -1;

	public CustomStack(int capacity) {
		this.capacity = capacity;
		this.stackArray = new Object[capacity];
	}

	public void AddElement(T element) {
		if (StackIsFull()) {
			System.out.println("Stack is full! Cannot add more elements.××");
			return;
		}
		stackArray[++top] = element;
	}

	public void RemoveElement() {
		if (StackIsEmpty()) {
			System.out.println("Stack is empty! No element to remove.Ø");
			return;
		}
		top--;
	}

	public T LastInStack() {
		if (StackIsEmpty()) {
			System.out.println("Stack is empty! No element to return.Ø");
			return null;
		}
		return (T) stackArray[top];
	}

	public boolean StackIsEmpty() {
		return top == -1;
	}

	public boolean StackIsFull() {
		return top == capacity - 1;
	}

	public void setCapacity(int newCapacity) {
		if (newCapacity < top + 1) {
			System.out.println("Cannot set capacity to a value less than the current number of elements.");
			return;
		}

		Object[] newArray = new Object[newCapacity];
		System.arraycopy(stackArray, 0, newArray, 0, top + 1);
		stackArray = newArray;
		capacity = newCapacity;
	}

	public int getCapacity() {
		return capacity;
	}
}
