package Building;

import java.util.Iterator;

import Building.dwelling.DwellingFloor;
import Building.dwelling.hotel.HotelFloor;
import Building.office.OfficeFloor;

public class FloorIterator implements Iterator<Space> {
	private Floor floor;
	int index=0;
public FloorIterator(Floor df){
	floor=df;
}


@Override
public Space next()
{
	index++;
		return floor.getOneSpace(index); 
	

}
@Override
public boolean hasNext() {

	return index!=floor.getNumberOfSpaces();
}
@Override
public void remove() {
	// TODO Auto-generated method stub
	
}
}
