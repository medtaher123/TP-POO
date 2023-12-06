public class MallardDuck extends Duck implements Flyable, Quackable {

	@Override
	public void quack() {
		System.out.println("mallardDuck is Quacking!");

	}

	@Override
	public void fly() {
		System.out.println("mallardDuck is Flying!");
		
	}

	@Override
	public void display() {
		System.out.println("this is mallardDuck!");
	}


	
}