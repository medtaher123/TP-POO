public class RubberDuck extends Duck implements Quackable {

	@Override
	public void quack() {
		System.out.println("RubberDuck is Quacking!");

	}

	@Override
	public void display() {
		System.out.println("this is RubberDuck!");
	}

}
