package Building;

//Ошибка некорретной площади помещения 
public class InvalidSpaceAreaException extends IllegalArgumentException{
	private double detail;

	public InvalidSpaceAreaException(double a) {

	detail = a;

	}

	public String toString() {

		return "Введеная площадь <" + detail + "> является некорректоной";
	}

}
