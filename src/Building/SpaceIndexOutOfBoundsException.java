package Building;

// ошибка выхода за границы номеров помещений
public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{

	private int detail;

	public SpaceIndexOutOfBoundsException(int a) {

	detail = a;

	}

	public String toString() {

	return "Введеный номер -" + detail + "- находится вне границы номеров помещений";

	}

}
