package Building;
//������ ������������ ���������� ������ � ��������� 
public class InvalidRoomsCountException extends IllegalArgumentException{
	private int detail;

	public InvalidRoomsCountException(int a) {

	detail = a;

	}

	public String toString() {

		return "��������� ����������� ������ <" + detail + "> �������� �������������";
	}
}
