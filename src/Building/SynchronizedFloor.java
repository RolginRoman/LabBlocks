package Building;

import java.util.Iterator;

public class SynchronizedFloor implements Floor{

	private Floor floor;
	
	public SynchronizedFloor(Floor floor){
		this.floor=floor;
	}
	
	@Override
	public synchronized int getNumberOfSpaces() {
		
		return floor.getNumberOfSpaces();
	}

	@Override
	public synchronized  int getNumberRoomsOfSpaces() {
		return floor.getNumberRoomsOfSpaces();
	}

	@Override
	public synchronized double getAllAreaOfFloor() {
		return floor.getAllAreaOfFloor();
	}

	@Override
	public synchronized Space[] getMassSpaces() {
		return floor.getMassSpaces();
	}

	@Override
	public synchronized Space getOneSpace(int number) {
		return floor.getOneSpace(number);
	}

	@Override
	public synchronized void changeSpace(int number, Space newSpace) {
		 floor.changeSpace(number, newSpace);
		
	}

	@Override
	public synchronized void addNewSpace(int number) {
		floor.addNewSpace(number);
		
	}

	@Override
	public synchronized void deleteSpace(int number) {
		floor.deleteSpace(number);
		
	}

	@Override
	public synchronized Space getBestSpace() {
		return floor.getBestSpace();
	}

	@Override
	public void addFitSpace(int number, Space newSpace) {
		floor.addFitSpace(number, newSpace);
		
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	public synchronized Object clone(){
		// TODO Auto-generated method stub
				return floor.clone();
}

}
