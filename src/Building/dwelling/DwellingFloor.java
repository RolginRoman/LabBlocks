package Building.dwelling;

import java.util.Iterator;

import Building.Floor;
import Building.Space;
import Building.office.OfficeFloor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DwellingFloor implements Floor, java.io.Serializable {

//   private int numberFlatOfFloor;
 //   private Space[] flatOfFloor;
    private List<Space> spaces = new ArrayList<>();

    public DwellingFloor(int numberFlatOfFloor) {
        for (int i=0;i<numberFlatOfFloor;i++)
            spaces.add(new Flat());
    }

    public DwellingFloor(Space... flatOfFloor) {
        spaces.addAll(Arrays.asList(flatOfFloor));
    }

    @Override
    public int getNumberOfSpaces() {
        return spaces.size();
    }

    @Override
    public double getTotalArea() {
        double area = 0;
        Iterator<Space> iter = spaces.iterator();
        while (iter.hasNext()) {
            area += iter.next().getArea();
        }
//        for (int i = 0; i < numberFlatOfFloor; i++) {
//            area += flatOfFloor[i].getArea();
//        }
        return area;
    }

    @Override
    public int getNumberRoomsOfSpaces() {
        int amt = 0;
        Iterator<Space> iter = spaces.iterator();
        while (iter.hasNext()) {
            amt += iter.next().getAmtOfRoom();
        }
//        for (int i = 0; i < numberFlatOfFloor; i++) {
//            amt = amt + flatOfFloor[i].getAmtOfRoom();
//        }
        return amt;
    }

    @Override
    public Space[] getSpacesArray() {
        Space[] sps = new Space[spaces.size()];
        return spaces.toArray(sps);
    }

    @Override
    public Space getSpaceByNum(int i) {
        if (i > spaces.size()) {
            throw new IndexOutOfBoundsException();
        }
        return spaces.get(i);
        //return flatOfFloor[i];
    }

    @Override
    public void changeSpace(int i, Space newFlat) {
        spaces.set(i, newFlat);
    }

    @Override
    public void deleteSpace(int index)// �� ������ � ������������
    {
        if (index > spaces.size()) {
            throw new IndexOutOfBoundsException();
        }
        spaces.remove(index);
//        Space[] newFlatsOfLloor = new Flat[flatOfFloor.length - 1];
//        int j = 0;
//        for (int i = 0; i < flatOfFloor.length; i++) {
//            if (i != index) {
//                newFlatsOfLloor[j] = flatOfFloor[i];
//                j++;
//            }
//
//        }
//        flatOfFloor = newFlatsOfLloor;
    }

    @Override
    public void addFitSpace(int index, Space newFlat) {
        if (index > spaces.size()) {
            throw new IndexOutOfBoundsException();
        }
        spaces.add(index, newFlat);
//        Space[] newFlatsOfLloor = new Flat[flatOfFloor.length + 1];
//        int j = 0;
//        for (int i = 0; i < flatOfFloor.length; i++) {
//
//            if (i == index) {
//                newFlatsOfLloor[j] = newFlat;
//                j++;
//            }
//
//            newFlatsOfLloor[j] = flatOfFloor[i];
//        }
//        flatOfFloor = newFlatsOfLloor;
    }

    @Override
    public Space getBestSpace() {
        double area = 0;
        Iterator<Space> iter = spaces.iterator();
        Space cur,res=spaces.get(0);
        while (iter.hasNext()) {
            if ((cur=iter.next()).getArea() > area);
            {
                res=cur;
            }
        }
        return res;
//        double area = 0;
//        int numbFlat = 0;
//        for (int i = 0; i < flatOfFloor.length; i++) {
//            if (flatOfFloor[i].getArea() > area) {
//                numbFlat = i;
//                area = flatOfFloor[i].getArea();
//            }
//        }
//        return flatOfFloor[numbFlat];
    }

    @Override
    public void addNewSpace(int number) {
    }

    @Override
    public Iterator iterator() {

        return spaces.iterator();
    }

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
            Floor ofFloor = (Floor) object;
            if (this.getNumberOfSpaces() == ofFloor.getNumberOfSpaces()) {
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

    @Override
    public int hashCode() {
        int code = this.getNumberOfSpaces();
        for (int i = 0; i < this.getNumberOfSpaces(); i++) {
            code = (code ^ this.getSpaceByNum(i).hashCode());
        }
        return code;
    }

//    private class FloorIterator implements Iterator<Space> {
//
//        int index = 0;
//
//        @Override
//        public Space next() {
//            return flatOfFloor[index++];
//        }
//
//        @Override
//        public boolean hasNext() {
//            return index != flatOfFloor.length;
//        }
//
//        @Override
//        public void remove() {
//            // TODO Auto-generated method stub
//
//        }
//    }

}
