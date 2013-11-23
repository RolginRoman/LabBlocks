package Building;

public class InexchangeableSpacesException extends Exception {
	private int space1;
	private int space2;
	

	InexchangeableSpacesException(int a, int b) {

	space1 = a;
	space2 = b;
   
	}

	public String toString() {

		return "Выбранные помещения  <" + space1 + ">  и <" + space2 + "> выбраных вами этажей не подходят для обмена";
	}
}
