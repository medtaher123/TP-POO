public class DuckTest {

	public static void main(String[] args) {
		Duck decoyDuck = new DecoyDuck();
		decoyDuck.display();
		decoyDuck.swim();

		Duck mallardDuck = new MallardDuck();
		mallardDuck.display();
		mallardDuck.swim();
		((Flyable) mallardDuck).fly();
		((Quackable) mallardDuck).quack();

		Duck redHeadDuck = new RedheadDuck();
		redHeadDuck.display();
		redHeadDuck.swim();
		((Flyable) redHeadDuck).fly();

		Duck rubberDuck = new RubberDuck();
		rubberDuck.display();
		rubberDuck.swim();
		((Quackable) rubberDuck).quack();
	}
}
