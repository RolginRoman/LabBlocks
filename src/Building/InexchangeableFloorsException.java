package Building;

public class InexchangeableFloorsException extends Throwable  { // нужно ли наследовать от Exception??
	private int floor1;
	private int floor2;

	InexchangeableFloorsException(int a, int b) {

		floor1 = a;
		floor2 = b;
	}

	public String toString() {

		return "Выбранные этажи  <" + floor1 + ">  и <" + floor2 + "> "
				+ " не подходят для обмена";
	}
}
