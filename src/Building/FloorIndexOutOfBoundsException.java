package Building;

// ������ ������ �� ������� ������� ������ 
public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException{
	private int detail;

	public FloorIndexOutOfBoundsException(int a) {

	detail = a;

	}

	public String toString() {

	return "�������� ����� <" + detail + "> ��������� ��� ������� ������� ������";

	}
}
