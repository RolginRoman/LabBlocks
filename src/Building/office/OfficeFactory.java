package Building.office;

import Building.Building;
import Building.BuildingFactory;
import Building.Floor;
import Building.Space;

public class OfficeFactory implements BuildingFactory {

	@Override
	public Space createSpace(double area) {
		Space office = new Office(area);
		return office;
	}

	@Override
	public Space createSpace(int roomsCount, double area) {
		Space office = new Office(roomsCount, area);
		return office;
	}

	@Override
	public Floor createFloor(int spacesCount) {
		Floor floor = new OfficeFloor(spacesCount);
		return floor;
	}

	@Override
	public Floor createFloor(Space[] spaces) {
		Floor floor = new OfficeFloor(spaces);
		return floor;
	}

	@Override
	public Building createBuilding(int floorsCount, int[] spacesCounts) {
		Building build = new OfficeBuilding(floorsCount, spacesCounts);
		return build;
	}

	@Override
	public Building createBuilding(Floor[] floors) {
		Building build = new OfficeBuilding(floors);
		return build;
	}

}
