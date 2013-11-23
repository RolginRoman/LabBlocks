package Building;

import java.util.Iterator;

import Building.dwelling.DwellingFloor;
import Building.office.OfficeFloor;

public class BuildingIterator implements Iterator<Floor> {

    private Building building;
    int index = 0;

    public BuildingIterator(Building building) {
        // TODO Auto-generated constructor stub
        this.building = building;
    }

    @Override
    public Floor next() {
        index++;
        return building.getOneFloor(index);

    }

    @Override
    public boolean hasNext() {
        return index != building.getNumberOfFloor();
    }

    @Override
    public void remove() {
	// TODO Auto-generated method stub

    }
}
