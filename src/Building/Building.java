package Building;

public interface Building {

    public int getFloorCount();

    public int getSpacesCount();

    public double getTotalArea();

    public int getRoomsCount();

    public Floor[] getMassFloor();

    public Floor getFloorByNum(int number);

	//public   void changeSpace(int number, Floor newFloor);
    public Space getSpaceByNum(int number);

    public void changeSpace(int number, Space newOffice);

    public void changeFloor(int number, Floor newFloor);

    public void addSpace(int number, Space newOffice)
            throws FloorIndexOutOfBoundsException;

    public void deleteSpace(int number)
            throws FloorIndexOutOfBoundsException;

    public Space getBestSpace();

    public Space[] sortSpace();

    public Object clone();

    java.util.Iterator iterator();

}
