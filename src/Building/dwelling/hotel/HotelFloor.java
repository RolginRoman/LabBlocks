package Building.dwelling.hotel;

import Building.Floor;
import Building.Space;
import Building.dwelling.DwellingFloor;
import Building.office.OfficeFloor;

public class HotelFloor extends DwellingFloor {

    public HotelFloor(int numberFlatOfFloor) {
        super(numberFlatOfFloor);
        numberStar = 1;
    }

    public HotelFloor(Space... masSpace) {
        super(masSpace);
        numberStar = 1;
    }

    private int numberStar;

    public int getNumbStar() {
        return numberStar;
    }

    public void setNumbStar(int numb) {
        numberStar = numb;
    }

//    public String toString() {
//        StringBuilder stringFloor = new StringBuilder();
//        stringFloor.append(this.getClass().getSimpleName() + " ( ");
//        stringFloor.append(this.getNumberOfSpaces());
//        for (int i = 0; i < this.getNumberOfSpaces(); i++) {
//            stringFloor.append(this.getSpaceByNum(i).toString());
//        }
//        return stringFloor.toString();
//    }
    
    @Override
    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        stringFloor.append(this.getClass().getSimpleName());
        stringFloor.append(" (");
        for (int i = 0; i < getNumberOfSpaces() - 1; i++) {
            stringFloor.append(getSpaceByNum(i));
            stringFloor.append(", ");
        }
        stringFloor.append(getSpaceByNum(getNumberOfSpaces() - 1));
        return stringFloor.append(")").toString();
    }

    public boolean equals(Object object) {
        if (object != null) {
            HotelFloor ofFloor = (HotelFloor) object;
            if ((this.getNumberOfSpaces() == ofFloor.getNumberOfSpaces()) && (this.getNumbStar() == ofFloor.getNumbStar())) {
                for (int i = 0; i < ofFloor.getNumberOfSpaces(); i++) {
                    if (!ofFloor.getSpaceByNum(i).equals(this.getSpaceByNum(i))) {
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

        for (int i = 0; i < this.getNumberOfSpaces(); i++) {
            ((OfficeFloor) obj).addFitSpace(i, this.getSpaceByNum(i));
        }
        return obj;

    }

    @Override
    public int hashCode() {
        int code = this.getNumberOfSpaces() + this.getNumbStar();
        for (int i = 0; i < this.getNumberOfSpaces(); i++) {
            code = (code ^ this.getSpaceByNum(i).hashCode());
        }
        return code;
    }

}
