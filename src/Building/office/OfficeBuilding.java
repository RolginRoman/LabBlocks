package Building.office;

import java.util.Iterator;

import Building.Building;
import Building.BuildingIterator;
import Building.Floor;
import Building.FloorIndexOutOfBoundsException;
import Building.Space;

//import Building.OfficeFloor.Node;
public class OfficeBuilding implements Building, java.io.Serializable {

    private class Node implements java.io.Serializable {

        private Node() {
            floor = new OfficeFloor(2);
        }

        public Node(int i) {
            floor = new OfficeFloor(i);
        }

        public Node(Node next) {
            this.next = next;
        }

        public Node(Floor floor) {
            this.floor = floor;
        }

        private Floor floor;
        private Node next;
        private Node previous;

        public Floor getElement() {
            return floor;
        }

        public void setElement(Floor e) {
            floor = e;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node n) {
            previous = n;
        }
    }

    public OfficeBuilding(int numberOfFloor, int[] floorMass) {

        this.numberOfFloor = numberOfFloor;
        head = new Node(floorMass[0]);
        Node lastNode = head;
        for (int i = 1; i < numberOfFloor; i++) {
            Node node = new Node(floorMass[i]);
            lastNode.setNext(node);
            node.setPrevious(lastNode);
            lastNode = node;
            if (i == numberOfFloor - 1) {
                node.setNext(head);
                head.setPrevious(node);
            }
        }
    }

    public OfficeBuilding(Floor[] masFloor) {
        numberOfFloor = masFloor.length;
        head = new Node();
        head.setElement(masFloor[0]);
        if (numberOfFloor != 1) {
            Node lastNode = head;

            for (int i = 1; i < numberOfFloor; i++) {
                Node node = new Node(masFloor[i]);
                lastNode.setNext(node);
                node.setPrevious(lastNode);
                lastNode = node;
                if (i == numberOfFloor - 1) {
                    node.setNext(head);
                    head.setPrevious(node);
                }
            }
        } else {
            head.setNext(head);
            head.setPrevious(head);
        }
    }
    Node head;
    int numberOfFloor;

    private Node getFloor(int number) {
        Node node;
        if (number != 0) {
            node = head;
            for (int i = 0; i < number; i++) {
                node = node.getNext();
            }
            return node;
        } else {
            return head;
        }
    }

    private void addOffice(int number) {
        Node newNode = new Node(2);

        newNode.setNext(this.getFloor(number));
        newNode.setPrevious(this.getFloor(number).getPrevious());
        this.getFloor(number).setPrevious(newNode);
        if (number == 0) {
            head = newNode;
        }
        numberOfFloor++;
    }

    private void delOffice(int number) {

        this.getFloor(number).getPrevious().setNext(this.getFloor(number).getNext());
        this.getFloor(number).getNext().setPrevious(this.getFloor(number).getPrevious());
        if (number == 0) {
            head = this.getFloor(number).getNext();
        }
        numberOfFloor--;
    }

    /* (non-Javadoc)
     * @see Building.Building#getNumberOfFloor()
     */
    @Override
    public int getNumberOfFloor() {
        if (head != null) {
            Node node = head;
            int number = 1;
            while (node.getNext() != head) {
                node = node.getNext();
                number++;
            }
            return number;
        } else {
            return 0;
        }
    }

    /* (non-Javadoc)
     * @see Building.Building#getNumberOfficeOfBuilding()
     */
    @Override
    public int getNumberSpaceOfBuilding() {
        if (head != null) {
            int number = 0;
            Node node = head;
            for (int i = 0; i < this.getNumberOfFloor(); i++) {
                number += node.getElement().getNumberOfSpaces();
                node = node.getNext();
            }
            return number;
        } else {
            return 0;
        }
    }

    /* (non-Javadoc)
     * @see Building.Building#getAllAreaOfBuilding()
     */
    @Override
    public double getAllAreaOfBuilding() {
        if (head != null) {
            int area = 0;
            Node node = head;
            for (int i = 0; i < this.getNumberOfFloor(); i++) {
                area += node.getElement().getAllAreaOfFloor();
                node = node.getNext();
            }
            return area;
        } else {
            return 0;
        }
    }

    /* (non-Javadoc)
     * @see Building.Building#getNumberRoomsOfBuilding()
     */
    @Override
    public int getNumberRoomsOfBuilding() {
        if (head != null) {
            int number = 0;
            Node node = head;
            for (int i = 0; i < this.getNumberOfFloor(); i++) {
                number += node.getElement().getNumberRoomsOfSpaces();
                node = node.getNext();
            }
            return number;
        } else {
            return 0;
        }
    }

    /* (non-Javadoc)
     * @see Building.Building#getMassFloor()
     */
    @Override
    public Floor[] getMassFloor() {
        Floor[] floorMas = new Floor[this.getNumberOfFloor()];
        if (head != null) {
            floorMas[0] = head.getElement();
            Node node = head;
            for (int i = 1; i < this.getNumberOfFloor(); i++) {
                node = node.getNext();
                floorMas[i] = node.getElement();
            }
            return floorMas;
        } else {
            return floorMas;
        }
    }
    /* (non-Javadoc)
     * @see Building.Building#getOneFloor(int)
     */

    @Override
    public Floor getOneFloor(int number) {
        if (this.getNumberOfFloor() <= number || number < 0) {
            throw new FloorIndexOutOfBoundsException(number);
        }
        return this.getFloor(number).getElement();
    }

    /* (non-Javadoc)
     * @see Building.Building#changeOffice(int, Building.Floor)
     */
    @Override
    public void changeFloor(int number, Floor newFloor) {
        if (this.getNumberOfFloor() <= number || number < 0) {
            throw new FloorIndexOutOfBoundsException(number);
        }
        Node node = new Node(newFloor);
        this.getFloor(number).getPrevious().setNext(node);
        node.setNext(this.getFloor(number).getNext());
        node.setPrevious(this.getFloor(number).getPrevious());
        this.getFloor(number).getNext().setPrevious(node);
        if (number == 0) {
            head = node;
        }
    }

    /* (non-Javadoc)
     * @see Building.Building#getOneOffice(int)
     */
    @Override
    public Space getOneSpace(int number) {
        if (this.getNumberOfFloor() <= number || number < 0) {
            throw new FloorIndexOutOfBoundsException(number);
        }
        int count = 0;
        Node node = head;
        for (int i = 0; i < this.getNumberOfFloor(); i++) {
            if ((number > count) && (number < count + node.getElement().getNumberOfSpaces())) {
                return node.getElement().getOneSpace(number - count);
            }
            count += node.getElement().getNumberOfSpaces();
            node = node.getNext();

        }
        return null;
    }

    /* (non-Javadoc)
     * @see Building.Building#changeOffice(int, Building.Office)
     */
    @Override
    public void changeSpace(int number, Space newOffice) {
        if (this.getNumberOfFloor() <= number || number < 0) {
            throw new FloorIndexOutOfBoundsException(number);
        }
        int count = 0;
        Node node = head;
        for (int i = 0; i < this.getNumberOfFloor(); i++) {
            if ((number > count) && (number < count + node.getElement().getNumberOfSpaces())) {
                node.getElement().changeSpace(number - count, newOffice);
                break;
            }
            count += node.getElement().getNumberOfSpaces();
            node = node.getNext();

        }

    }

    /* (non-Javadoc)
     * @see Building.Building#addOffice(int, Building.Office)
     */
    @Override
    public void addSpace(int number, Space newOffice) {
        if (this.getNumberOfFloor() <= number || number < 0) {
            throw new FloorIndexOutOfBoundsException(number);
        }
        int count = 0;
        Node node = head;
        for (int i = 0; i < this.getNumberOfFloor(); i++) {
            if ((number > count) && (number < count + node.getElement().getNumberOfSpaces())) {
                node.getElement().addFitSpace(number, newOffice);
                break;
            }
            count += node.getElement().getNumberOfSpaces();
            node = node.getNext();
        }
    }
    /* (non-Javadoc)
     * @see Building.Building#deleteOffice(int)
     */

    @Override
    public void deleteSpace(int number) {
        if (this.getNumberOfFloor() <= number || number < 0) {
            throw new FloorIndexOutOfBoundsException(number);
        }
        int count = 0;
        Node node = head;
        for (int i = 0; i < this.getNumberOfFloor(); i++) {
            if ((number > count) && (number < count + node.getElement().getNumberOfSpaces())) {
                node.getElement().deleteSpace(number);
                break;
            }
            count += node.getElement().getNumberOfSpaces();
            node = node.getNext();
        }
    }

    /* (non-Javadoc)
     * @see Building.Building#getBestSpace()
     */
    @Override
    public Space getBestSpace() {
        double maxArea = 0;
        Node node = head;
        Space maxAreaOffice = new Office();
        for (int i = 0; i < this.getNumberOfFloor(); i++) {
            if (node.getElement().getBestSpace().getArea() > maxArea) {
                maxArea = node.getElement().getBestSpace().getArea();
                maxAreaOffice = node.getElement().getBestSpace();
            }
            node = node.getNext();
        }
        return maxAreaOffice;
    }

    /* (non-Javadoc)
     * @see Building.Building#sortOffice()
     */
    @Override
    public Space[] sortSpace() {
        Space[] sortMasOffice = new Space[this.getNumberSpaceOfBuilding()];
        double maxArea = 0;

        for (int i = 0; i < this.getNumberSpaceOfBuilding(); i++) {
            maxArea = 0;
            for (int j = 0; j < this.getNumberOfFloor(); j++) {
                for (int k = 0; k < this.getFloor(j).getElement().getNumberOfSpaces(); k++) {
                    if ((maxArea <= this.getFloor(j).getElement().getOneSpace(k).getArea()) && (this.proverk(sortMasOffice, this.getFloor(j).getElement().getOneSpace(k)))) {
                        maxArea = this.getFloor(j).getElement().getOneSpace(k).getArea();
                        sortMasOffice[i] = this.getFloor(j).getElement().getOneSpace(k);
                    }
                }
            }

        }
        return sortMasOffice;
    }

    public boolean proverk(Space[] mass, Space office) // �� ������� �� ���� ������� ��� � ������
    {
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == office) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator iterator() {
        return new BuildingIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        stringFloor.append(getClass().getSimpleName());
        stringFloor.append(" ( ");
        for (int i = 0; i < getNumberOfFloor(); i++) {
            stringFloor.append(getOneFloor(i).toString());
        }
        return stringFloor.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null) {
            Building buil = (Building) object;
            if (this.getNumberOfFloor() == buil.getNumberOfFloor()) {
                for (int i = 0; i < buil.getNumberOfFloor(); i++) {
                    if (!buil.getOneFloor(i).equals(this.getOneFloor(i))) {
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
            for (int i = 0; i < this.getNumberOfFloor(); i++) {
                ((OfficeBuilding) obj).changeFloor(i, (Floor) this.getOneFloor(i).clone());
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
        int code = this.getNumberOfFloor();
        for (int i = 0; i < this.getNumberOfFloor(); i++) {
            code = (code ^ this.getOneFloor(i).hashCode());
        }
        return code;
    }
}
