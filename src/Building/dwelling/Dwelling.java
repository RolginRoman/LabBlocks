package Building.dwelling;

import java.util.Iterator;

import Building.Building;
import Building.Floor;
import Building.Space;
import Building.office.OfficeFloor;

public class Dwelling implements Building, java.io.Serializable {

    private int numberFloor;
    private Floor[] allDwellingFloor;

    public Dwelling(int numberFloor, int[] spacesCounts) {
        this.numberFloor = numberFloor;
        this.allDwellingFloor = new DwellingFloor[numberFloor];
    }

    public Dwelling(Floor... allDwellingFloor) {
        this.allDwellingFloor = allDwellingFloor;
        numberFloor = allDwellingFloor.length;
    }

    public int getFloorCount() {
        return numberFloor;
    }

    @Override
    public int getSpacesCount() {
        int number = 0;
        for (int i = 0; i < numberFloor; i++) {
            try {
                number += allDwellingFloor[i].getNumberOfSpaces();
            }
            catch (Exception e) {
                System.out.print("getNum" + e);
            }
        }
        return number;
    }

    @Override
    public double getTotalArea() throws NullPointerException {
        int area = 0;
        for (int i = 0; i < numberFloor; i++) {
            area += allDwellingFloor[i].getTotalArea();
        }
        return area;
    }

    @Override
    public int getRoomsCount() {
        int number = 0;
        for (int i = 0; i < numberFloor; i++) {
            number += allDwellingFloor[i].getNumberRoomsOfSpaces();
        }
        return number;
    }

    @Override
    public Floor[] getMassFloor() {
        return allDwellingFloor;
    }

    @Override
    public Floor getFloorByNum(int i) {
        return allDwellingFloor[i];
    }

    @Override
    public Space getSpaceByNum(int number) {
        int count = 0;

        for (int i = 0; i < allDwellingFloor.length; i++) {
            if ((count <= number)
                    && (number <= (count + allDwellingFloor[i]
                    .getNumberOfSpaces())))// �� ���� �� �����
            // ��������� ��������?
            {
                return allDwellingFloor[i].getSpaceByNum(number - count);
            } else {
                count += allDwellingFloor[i].getNumberOfSpaces();
            }
        }
        return null;
    }

    @Override
    public void changeSpace(int number, Space newFlat) {
        int count = 0;
        for (int i = 0; i < allDwellingFloor.length; i++) {
            if ((count <= number)
                    && (number <= (count + allDwellingFloor[i]
                    .getNumberOfSpaces())))// �� ���� �� �����
            // ��������� ��������?
            {

                allDwellingFloor[i].changeSpace(number - count, newFlat);
            } else {
                count += allDwellingFloor[i].getNumberOfSpaces();
            }
        }
    }

    @Override
    public void changeFloor(int number, Floor newFloor) {
        allDwellingFloor[number] = newFloor;
    }

    @Override
    public void deleteSpace(int number)// �� ������ � ������������
    {
        int count = 0;

        for (int i = 0; i < allDwellingFloor.length; i++) {
            if ((count <= number)
                    && (number <= (count + allDwellingFloor[i]
                    .getNumberOfSpaces())))// �� ���� �� �����
            // ��������� ��������?
            {
                allDwellingFloor[i].deleteSpace(number - count);
            } else {
                count += allDwellingFloor[i].getNumberOfSpaces();
            }
        }
    }

    @Override
    public void addSpace(int number, Space newFlat) {
        int count = 0;

        for (int i = 0; i < allDwellingFloor.length; i++) {
            if ((count <= number)
                    && (number <= (count + allDwellingFloor[i]
                    .getNumberOfSpaces())))// �� ���� �� �����
            // ��������� ��������?
            {
                allDwellingFloor[i].addFitSpace(number - count, newFlat);
            } else {
                count += allDwellingFloor[i].getNumberOfSpaces();
            }
        }

    }

    @Override
    public Space getBestSpace() {
        double area = 0;
        int numbFloor = 0;
        Space numbFlat = new Flat();
        for (int i = 0; i < allDwellingFloor.length; i++) {
            if (allDwellingFloor[i].getBestSpace().getArea() > area) {
                area = allDwellingFloor[i].getBestSpace().getArea();
                numbFlat = allDwellingFloor[i].getBestSpace();
                numbFloor = i;
            }
        }
        return numbFlat;
    }

    @Override
    public Space[] sortSpace() {
        Space[] massFlat = new Flat[this.getSpacesCount()];

        Space lastMaxFlat = new Flat(2, this.getBestSpace().getArea());
        double max = 0;
        for (int k = 0; k < massFlat.length; k++) {
            max = 0;
            for (int i = 0; i < allDwellingFloor.length; i++) {
                for (int j = 0; j < allDwellingFloor[i].getNumberOfSpaces(); j++) {
                    if ((allDwellingFloor[i].getSpaceByNum(j).getArea() >= max)
                            && (allDwellingFloor[i].getSpaceByNum(j)
                            .getArea() <= lastMaxFlat.getArea())
                            && (lastMaxFlat != allDwellingFloor[i]
                            .getSpaceByNum(j))) {

                        max = allDwellingFloor[i].getSpaceByNum(j)
                                .getArea();// ���������� ������������ �� ������
                        // ������
                        massFlat[k] = allDwellingFloor[i].getSpaceByNum(j);
                    }
                }
            }

            lastMaxFlat = massFlat[k];// ���������� ��������� ������������
            // �������
            // ����� �� ���������� �������� ��������
            // �������
        }
        return massFlat;
    }

    @Override
    public Iterator iterator() {
        return new BuildingIterator();
    }

    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        stringFloor.append(this.getClass().getSimpleName() + " (");
        for (int i = 0; i < this.getFloorCount() - 1; i++) {
            stringFloor.append(this.getFloorByNum(i).toString());
            stringFloor.append(", ");
        }
        stringFloor.append(getFloorByNum(getFloorCount() - 1));
        return stringFloor.append(")").toString();
    }

    public boolean equals(Object object) {
        if (object != null) {
            Building buil = (Building) object;
            if (this.getFloorCount() == buil.getFloorCount()) {
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

        try {
            obj = super.clone();
            for (int i = 0; i < this.getFloorCount(); i++) {
                ((Dwelling) obj).changeFloor(i, (Floor) this.getFloorByNum(i).clone());
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
        int code = this.getFloorCount();
        for (int i = 0; i < this.getFloorCount(); i++) {
            code = (code ^ this.getFloorByNum(i).hashCode());
        }
        return code;
    }

    private class BuildingIterator implements Iterator<Floor> {

        int index = 0;

        @Override
        public Floor next() {
            return allDwellingFloor[index++];

        }

        @Override
        public boolean hasNext() {
            return index != allDwellingFloor.length;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub

        }
    }
}
