package Building.office;

import Building.InvalidRoomsCountException;
import Building.InvalidSpaceAreaException;
import Building.Space;
import Building.dwelling.Flat;

public class Office implements Space, java.io.Serializable {

    Office() {
        area = 250;
        amtOfRoom = 1;
    }

    Office(double area) throws InvalidSpaceAreaException {
        if (area <= 0) {
            throw new InvalidSpaceAreaException(area);
        }
        this.area = area;
        amtOfRoom = 1;
    }

    Office(int amtOfRoom) throws InvalidRoomsCountException {
        if (amtOfRoom < 0) {
            throw new InvalidRoomsCountException(amtOfRoom);
        }
        area = 250;
        this.amtOfRoom = amtOfRoom;
    }

    public Office(int amtOfRoom, double area) throws InvalidRoomsCountException, InvalidSpaceAreaException {
        if (area <= 0) {
            throw new InvalidSpaceAreaException(area);
        }
        if (amtOfRoom < 0) {
            throw new InvalidRoomsCountException(amtOfRoom);
        }
        this.area = area;
        this.amtOfRoom = amtOfRoom;
    }

    private double area;
    private int amtOfRoom;

    /*	public Node getHead()
     {
     return headSpace;
     }
     public void setHead(Node node)
     {
     this.headSpace=headSpace;
     }*/

    /* (non-Javadoc)
     * @see Space#setArea(double)
     */
    @Override
    public void setArea(double area) {
        this.area = area;
    }

    /* (non-Javadoc)
     * @see Space#getArea()
     */
    @Override
    public double getArea() {
        return area;
    }

    /* (non-Javadoc)
     * @see Space#setAmtOfRoom(int)
     */
    @Override
    public void setAmtOfRoom(int amtOfRoom) {
        this.amtOfRoom = amtOfRoom;
    }

    /* (non-Javadoc)
     * @see Space#getAmtOfRoom()
     */
    @Override
    public int getAmtOfRoom() {
        return amtOfRoom;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + this.getAmtOfRoom() + ", " + this.getArea() + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object != null) {
            Space space = (Space) object;
            return ((this.getAmtOfRoom() == space.getAmtOfRoom()) && (this.getArea() == space.getArea()));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int code = (this.getAmtOfRoom() ^ (int) (Double.doubleToLongBits(this.getArea()) >>> 32));
        code = (code ^ (int) Double.doubleToLongBits(this.getArea()));
        return code;
    }

    public Object clone() {
        Object obj;
        try {
            obj = super.clone();
            return obj;
        }
        catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

}
