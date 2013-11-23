package threads;

import Building.Floor;

public class Cleaner extends Thread{
private Floor floor;
	
	public Cleaner(Floor floor){
		this.floor=floor;
	}
	
	public void run() {
		 for(int i=0; i<floor.getNumberOfSpaces();i++){
			 System.out.println("Cleaning room number  " +i+" with total area " +floor.getOneSpace(i).getArea()+ " square meters ");	 
		 }
	
}
}
