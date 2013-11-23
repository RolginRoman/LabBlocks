package Building;
//Ошибка некорретного количества комнат в помещении 
public class InvalidRoomsCountException extends IllegalArgumentException{
	private int detail;

	public InvalidRoomsCountException(int a) {

	detail = a;

	}

	public String toString() {

		return "Введенное колличество комнат <" + detail + "> является некорректоной";
	}
}
