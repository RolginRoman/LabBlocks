package Building.dwelling;

import Building.Building;
import Building.BuildingFactory;
import Building.Floor;
import Building.Space;

public class DwellingFactory implements BuildingFactory {

	@Override
	public Space createSpace(double area) {
		Space space = new Flat(area);
		return space;
	}

	@Override
	public Space createSpace(int roomsCount, double area) {
		Space space = new Flat(roomsCount ,area);
		return space;
	}

	@Override
	public Floor createFloor(int spacesCount) {
		Floor floor = new DwellingFloor(spacesCount);
		return floor;
	}

	@Override
	public Floor createFloor(Space[] spaces) {
		Floor floor = new DwellingFloor(spaces);
		return floor;
	}

	@Override
	public Building createBuilding(int floorsCount, int[] spacesCounts) {
		Building build = new Dwelling(floorsCount, spacesCounts);
		return build;
	}

	@Override
	public Building createBuilding(Floor[] floors) {
		Building build = new Dwelling(floors);
		return build;
	}

}
