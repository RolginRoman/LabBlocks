package Building.dwelling.hotel;

import Building.Building;
import Building.BuildingFactory;
import Building.Floor;
import Building.Space;
import Building.dwelling.Flat;

public class HotelFactory implements BuildingFactory {

	@Override
	public Space createSpace(double area) {
		Space flat = new Flat(area);
		return flat;
	}

	@Override
	public Space createSpace(int roomsCount, double area) {
		Space flat = new Flat( roomsCount ,area);
		return flat;
	}

	@Override
	public Floor createFloor(int spacesCount) {
		Floor floor = new HotelFloor(spacesCount);
		return floor;
	}

	@Override
	public Floor createFloor(Space[] spaces) {
		Floor floor = new HotelFloor(spaces);
		return floor;
	}

	@Override
	public Building createBuilding(int floorsCount, int[] spacesCounts) {
		Building build = new Hotel(floorsCount, spacesCounts);
		return build;
	}

	@Override
	public Building createBuilding(Floor[] floors) {
		Building build = new Hotel(floors);
		return build;
	}

}
