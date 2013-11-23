package Building;

// ќшибка выхода за границы номеров этажей 
public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException{
	private int detail;

	public FloorIndexOutOfBoundsException(int a) {

	detail = a;

	}

	public String toString() {

	return "¬веденый номер <" + detail + "> находитс€ вне границы номеров этажей";

	}
}
