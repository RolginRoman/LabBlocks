package Building;

//������ ����������� ������� ��������� 
public class InvalidSpaceAreaException extends IllegalArgumentException{
	private double detail;

	public InvalidSpaceAreaException(double a) {

	detail = a;

	}

	public String toString() {

		return "�������� ������� <" + detail + "> �������� �������������";
	}

}
