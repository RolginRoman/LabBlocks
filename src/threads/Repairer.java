package threads;

import Building.Floor;
import Building.Space;

public class Repairer extends Thread{
private Floor floor;
	
	public Repairer(Floor floor){
		this.floor=floor;
	}
	
	public void run() {
		 for(int i=0; i<floor.getNumberOfSpaces();i++){
			 System.out.println("Repairing space number " +i+" with total area " +floor.getSpaceByNum(i).getArea()+ " square meters ");	 
		 }
	
}
}
