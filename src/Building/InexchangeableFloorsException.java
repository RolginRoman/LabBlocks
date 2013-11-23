package Building;

public class InexchangeableFloorsException extends Throwable  { // ����� �� ����������� �� Exception??
	private int floor1;
	private int floor2;

	InexchangeableFloorsException(int a, int b) {

		floor1 = a;
		floor2 = b;
	}

	public String toString() {

		return "��������� �����  <" + floor1 + ">  � <" + floor2 + "> "
				+ " �� �������� ��� ������";
	}
}
