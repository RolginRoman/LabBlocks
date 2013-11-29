package Building.dwelling.hotel;

import Building.Building;
import Building.Floor;
import Building.Space;
import Building.dwelling.Dwelling;
import Building.dwelling.Flat;

public class Hotel extends Dwelling {

    public Hotel(Floor... allDwellingFloor) {

        super(allDwellingFloor);

    }

    public Hotel(int floorsCount, int[] spacesCounts) {
        super(floorsCount, spacesCounts);
    }

    public int starHotel() {
        int max = 0;
        for (int i = 0; i < this.getFloorCount(); i++) {
            if (this.getFloorByNum(i) instanceof HotelFloor) {
                HotelFloor tmp = (HotelFloor) this.getFloorByNum(i);
                if (tmp.getNumbStar() > max) {
                    max = tmp.getNumbStar();
                }
            }
        }
        return max;

    }

    public Space getBestSpace() {
        double area = 0;
        double newArea = 0;
        Space numbFlat = new Flat();

        for (int i = 0; i < this.getFloorCount(); i++) {
            if (this.getFloorByNum(i) instanceof HotelFloor) {

                HotelFloor tmp = (HotelFloor) this.getFloorByNum(i);

                switch (tmp.getNumbStar()) {

                    case 1:
                        newArea = tmp.getBestSpace().getArea() * 0.25;
                        break;

                    case 2:
                        newArea = tmp.getBestSpace().getArea() * 0.5;
                        break;

                    case 3:
                        newArea = tmp.getBestSpace().getArea() * 1.00;
                        break;

                    case 4:
                        newArea = tmp.getBestSpace().getArea() * 1.25;
                        break;

                    case 5:
                        newArea = tmp.getBestSpace().getArea() * 1.5;
                        break;

                    default:
                        break;
                }
            } else {
                newArea = this.getFloorByNum(i).getBestSpace().getArea();
            }
            if (newArea > area) {
                area = newArea;
                numbFlat = this.getFloorByNum(i).getBestSpace();
            }
        }
        return numbFlat;
    }

    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        stringFloor.append(this.getClass().getSimpleName() + " (");
        stringFloor.append(this.starHotel() + " ");
        for (int i = 0; i < this.getFloorCount() - 1; i++) {
            stringFloor.append(this.getFloorByNum(i).toString());
            stringFloor.append(", ");
        }
        stringFloor.append(getFloorByNum(getFloorCount() - 1));
        return stringFloor.append(")").toString();
    }

    public boolean equals(Object object) {
        if (object != null) {
            Hotel buil = (Hotel) object;
            if (this.getFloorCount() == buil.getFloorCount() && (buil.starHotel() == this.starHotel())) {

                for (int i = 0; i < buil.getFloorCount(); i++) {

                    if (!buil.getFloorByNum(i).equals(this.getFloorByNum(i))) {
                        return false;
                    }

                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Object clone() {
        Object obj;

        obj = super.clone();
        for (int i = 0; i < this.getFloorCount(); i++) {
            ((Dwelling) obj).changeFloor(i, (Floor) this.getFloorByNum(i).clone());
        }
        return obj;

    }

    @Override
    public int hashCode() {
        int code = this.getFloorCount() + this.starHotel();
        for (int i = 0; i < this.getFloorCount(); i++) {
            code = (code ^ this.getFloorByNum(i).hashCode());
        }
        return code;
    }

}
