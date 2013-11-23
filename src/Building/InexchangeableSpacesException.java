package Building;

public class InexchangeableSpacesException extends Exception {
	private int space1;
	private int space2;
	

	InexchangeableSpacesException(int a, int b) {

	space1 = a;
	space2 = b;
   
	}

	public String toString() {

		return "��������� ���������  <" + space1 + ">  � <" + space2 + "> �������� ���� ������ �� �������� ��� ������";
	}
}
