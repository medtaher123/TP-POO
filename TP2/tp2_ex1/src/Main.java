public class Main {

	public static void main(String[] args) {

		CustomStack<Integer> stack = new CustomStack<>(5);

		stack.AddElement(10);
		stack.AddElement(20);
		stack.AddElement(30);
		stack.AddElement(40);
		stack.AddElement(50);

		stack.AddElement(60); // Trying to add more elements than capacity

		System.out.println("Top element: " + stack.LastInStack());

		stack.RemoveElement();
		System.out.println("After removing an element, top element: " + stack.LastInStack());

		stack.setCapacity(7);
		System.out.println("New capacity: " + stack.getCapacity());

		stack.AddElement(60);
		stack.AddElement(70);
		stack.AddElement(80);

		System.out.println("Top element: " + stack.LastInStack());

		stack.setCapacity(4);
	}
}
