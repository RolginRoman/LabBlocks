package Building.dwelling;

import Building.Space;


public class Flat implements Space, java.io.Serializable{
	public Flat() {
		area = defaultArea;
		amtOfRoom = defaultAmtOfRoom;
	}

	public Flat(double area) {
		this.area = area;
		amtOfRoom = defaultAmtOfRoom;
	}

	public Flat( int amtOfRoom, double area) {
		this.area = area;
		this.amtOfRoom = amtOfRoom;
	}

	private double area;
	private int amtOfRoom;
	final double defaultArea = 50;
	final int defaultAmtOfRoom = 2;
	@Override
	public void setArea(double area) {
		this.area = area;
	}
	@Override
	public double getArea() {
		return area;
	}
	@Override
	public void setAmtOfRoom(int amtOfRoom) {
		this.amtOfRoom = amtOfRoom;
	}
	@Override
	public int getAmtOfRoom() {
		return amtOfRoom;
	}
	@Override
	public String toString(){
		return this.getClass().getSimpleName()+" (" + this.getAmtOfRoom() + ", " + this.getArea() + ")";
	}
	@Override public boolean equals(Object object){
		if(object!=null)
		{
		Space space = (Space)object;
		return (( this.getAmtOfRoom() == space.getAmtOfRoom() )&& (this.getArea()==space.getArea()));
		}
		else  return false;
	}
	@Override public  int hashCode(){
		int code=(this.getAmtOfRoom() ^ (int)(Double.doubleToLongBits(this.getArea())>>> 32) );
		code= (code ^ (int)Double.doubleToLongBits(this.getArea()));
		return code;
	}
	public 	Object clone()  {
		Object obj;
		try {
			obj=super.clone();
			return obj;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}
