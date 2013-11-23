package Building;
public interface Space {

	public abstract void setArea(double area);

	public abstract double getArea();

	public abstract void setAmtOfRoom(int amtOfRoom);

	public abstract int getAmtOfRoom();
	
	public 	Object clone();

}