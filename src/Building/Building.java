package Building;


public interface Building {

	public abstract int getNumberOfFloor();

	public abstract int getNumberSpaceOfBuilding();

	public abstract double getAllAreaOfBuilding();

	public abstract int getNumberRoomsOfBuilding();

	public abstract Floor[] getMassFloor();

	public abstract Floor getOneFloor(int number);

	//public abstract void changeSpace(int number, Floor newFloor);

	public abstract Space getOneSpace(int number);

	public abstract void changeSpace(int number, Space newOffice);
	public abstract void changeFloor(int number, Floor newFloor);

	public abstract void addSpace(int number, Space newOffice)
			throws FloorIndexOutOfBoundsException;

	public abstract void deleteSpace(int number)
			throws FloorIndexOutOfBoundsException;

	public abstract Space getBestSpace();

	public abstract Space[] sortSpace();
	
    public 	Object clone();
	
	java.util.Iterator iterator();

}