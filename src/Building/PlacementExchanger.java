package Building;


public class PlacementExchanger {

	public static boolean maySpaceExchange(Space sp1, Space sp2)
	{
		if(( sp1.getAmtOfRoom() == sp2.getAmtOfRoom() ) && (sp1.getArea() == sp2.getArea() ))
		{
			return true;
		}
		else return false;
	}
	
	public static boolean mayFloorExchange(Floor f1, Floor f2)
	{
		if(( f1.getNumberOfSpaces() == f2.getNumberOfSpaces() ) && (f1.getAllAreaOfFloor() == f2.getAllAreaOfFloor() ))
		{
			return true;
		}
		else return false;
	}
	
	public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException
	{
		
		if(index1>floor1.getNumberOfSpaces()||index1<0)
			throw new SpaceIndexOutOfBoundsException(index1); 

		if(index2>floor2.getNumberOfSpaces()||index2<0)
			throw new SpaceIndexOutOfBoundsException(index2);
		if(!PlacementExchanger.maySpaceExchange(floor1.getOneSpace(index1), floor2.getOneSpace(index2)))
			throw new InexchangeableSpacesException(index1, index2);
		else 
		{
			Space exSpace = floor2.getOneSpace(index2);
			floor2.changeSpace(index2, floor1.getOneSpace(index1));
			floor1.changeSpace(index1, exSpace);
		}
		
	}
	
	public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) 
			throws InexchangeableFloorsException
	{
		if(index1>building1.getNumberOfFloor()||index1<0)
			throw new FloorIndexOutOfBoundsException(index1); 

		if(index2>building2.getNumberOfFloor()||index2<0)
			throw new FloorIndexOutOfBoundsException(index2);
		if(!PlacementExchanger.mayFloorExchange(building1.getOneFloor(index1), building2.getOneFloor(index2))){
			throw new InexchangeableFloorsException(index1, index2);
		}
			else
			{
				Floor exFloor = building2.getOneFloor(index2);
				building2.changeFloor(index2, building1.getOneFloor(index1));
				building1.changeFloor(index1, exFloor);
			}
		}
	
	
}
