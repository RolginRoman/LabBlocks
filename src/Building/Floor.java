package Building;


public interface Floor {

	public abstract int getNumberOfSpaces();

	public abstract int getNumberRoomsOfSpaces();

	public abstract double getAllAreaOfFloor();

	public abstract Space[] getMassSpaces();

	public abstract Space getOneSpace(int number);

	public abstract void changeSpace(int number, Space newSpace);

	public abstract void addNewSpace(int number);

	public abstract void deleteSpace(int number);
			
	public abstract Space getBestSpace();

	public abstract void addFitSpace(int number, Space newSpace);
	
	java.util.Iterator iterator();

	public abstract Object clone();

	//public 	Object clone();
}