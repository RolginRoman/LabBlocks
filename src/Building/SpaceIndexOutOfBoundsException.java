package Building;

// ������ ������ �� ������� ������� ���������
public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{

	private int detail;

	public SpaceIndexOutOfBoundsException(int a) {

	detail = a;

	}

	public String toString() {

	return "�������� ����� -" + detail + "- ��������� ��� ������� ������� ���������";

	}

}
