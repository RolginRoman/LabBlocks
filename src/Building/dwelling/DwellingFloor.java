package Building.dwelling;

import java.util.Iterator;

import Building.Floor;
import Building.FloorIterator;
import Building.Space;
import Building.office.OfficeFloor;

public class DwellingFloor implements Floor, java.io.Serializable {

    public DwellingFloor(int numberFlatOfFloor) {
        this.numberFlatOfFloor = numberFlatOfFloor;
        this.flatOfFloor = new Flat[numberFlatOfFloor];
    }

    public DwellingFloor(Space[] flatOfFloor) {
        this.flatOfFloor = flatOfFloor;
        numberFlatOfFloor = flatOfFloor.length;
    }

    private int numberFlatOfFloor;
    private Space flatOfFloor[];

    @Override
    public int getNumberOfSpaces() {
        return numberFlatOfFloor;
    }

    @Override
    public double getAllAreaOfFloor() {
        double area = 0;
        for (int i = 0; i < numberFlatOfFloor; i++) {
            area = area + flatOfFloor[i].getArea();
        }
        return area;
    }

    @Override
    public int getNumberRoomsOfSpaces() {
        int amt = 0;
        for (int i = 0; i < numberFlatOfFloor; i++) {
            amt = amt + flatOfFloor[i].getAmtOfRoom();
        }
        return amt;
    }

    @Override
    public Space[] getMassSpaces() {
        return flatOfFloor;
    }

    @Override
    public Space getOneSpace(int i) {
        return flatOfFloor[i];
    }

    @Override
    public void changeSpace(int i, Space newFlat) {
        flatOfFloor[i].setArea(newFlat.getArea());
        flatOfFloor[i].setAmtOfRoom(newFlat.getAmtOfRoom());
    }

    @Override
    public void deleteSpace(int index)// �� ������ � ������������
    {
        Space[] newFlatsOfLloor = new Flat[flatOfFloor.length - 1];
        int j = 0;
        for (int i = 0; i < flatOfFloor.length; i++) {
            if (i != index) {
                newFlatsOfLloor[j] = flatOfFloor[i];
                j++;
            }

        }
        flatOfFloor = newFlatsOfLloor;
    }

    @Override
    public void addFitSpace(int index, Space newFlat) {
        Space[] newFlatsOfLloor = new Flat[flatOfFloor.length + 1];
        int j = 0;
        for (int i = 0; i < flatOfFloor.length; i++) {

            if (i == index) {
                newFlatsOfLloor[j] = newFlat;
                j++;
            }

            newFlatsOfLloor[j] = flatOfFloor[i];
        }
        flatOfFloor = newFlatsOfLloor;
    }

    @Override
    public Space getBestSpace() {
        double area = 0;
        int numbFlat = 0;
        for (int i = 0; i < flatOfFloor.length; i++) {
            if (flatOfFloor[i].getArea() > area) {
                numbFlat = i;
                area = flatOfFloor[i].getArea();
            }
        }
        return flatOfFloor[0];
    }

    @Override
    public void addNewSpace(int number) {

    }

    @Override
    public Iterator iterator() {

        return new FloorIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        stringFloor.append(this.getClass().getSimpleName());
        stringFloor.append(" (");
        for (int i = 0; i < getNumberOfSpaces() - 1; i++) {
            stringFloor.append(getOneSpace(i));
            stringFloor.append(", ");
        }
        stringFloor.append(getOneSpace(getNumberOfSpaces() - 1));
        return stringFloor.append(")").toString();
    }

    public boolean equals(Object object) {
        if (object != null) {
            Floor ofFloor = (Floor) object;
            if (this.getNumberOfSpaces() == ofFloor.getNumberOfSpaces()) {
                for (int i = 0; i < ofFloor.getNumberOfSpaces(); i++) {
                    if (!ofFloor.getOneSpace(i).equals(this.getOneSpace(i))) {
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

        try {
            obj = super.clone();
            for (int i = 0; i < this.getNumberOfSpaces(); i++) {
                ((OfficeFloor) obj).addFitSpace(i, (Space) this.getOneSpace(i).clone());
            }
            return obj;
        }
        catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int hashCode() {
        int code = this.getNumberOfSpaces();
        for (int i = 0; i < this.getNumberOfSpaces(); i++) {
            code = (code ^ this.getOneSpace(i).hashCode());
        }
        return code;
    }

}
