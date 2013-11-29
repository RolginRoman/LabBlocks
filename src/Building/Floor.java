package Building;


public interface Floor {

	public  int getNumberOfSpaces();

	public  int getNumberRoomsOfSpaces();

	public  double getTotalArea();

	public  Space[] getSpacesArray();

	public  Space getSpaceByNum(int number);

	public  void changeSpace(int number, Space newSpace);

	public  void addNewSpace(int number);

	public  void deleteSpace(int number);
			
	public  Space getBestSpace();

	public  void addFitSpace(int number, Space newSpace);
	
	java.util.Iterator iterator();

	public  Object clone();
}