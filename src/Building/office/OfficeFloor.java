package Building.office;

import java.util.Iterator;

import Building.Floor;
import Building.Space;
import Building.SpaceIndexOutOfBoundsException;

//import Building.Office.Node;
public class OfficeFloor implements Floor, java.io.Serializable {

    private class Node implements java.io.Serializable {

        private Space office;
        private Node next;
        public Node() {
            office = new Office();
        }

        public Node(Node next) {
            this.next = next;
        }

        public Node(Space office) {
            this.office = office;
        }

        public Space getElement() {
            return office;
        }

        public void setElement(Space e) {
            office = e;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }
    }

    private class FloorIterator implements Iterator<Space> {

    int index = 0;

    @Override
    public Space next() {
        return getSpaceByNum(index++);

    }

    @Override
    public boolean hasNext() {

        return index != getNumberOfSpaces();
    }

    @Override
    public void remove() {
	// TODO Auto-generated method stub

    }
}
    
    public OfficeFloor(int numberOfOffice) {
        //Space office=new Office();
        head = new Node();

        Node lastOffice = head;
        for (int i = 1; i < numberOfOffice; i++) {
            Node office = new Node();
            lastOffice.setNext(office);
            lastOffice = office;
            if (i == numberOfOffice - 1) {
                office.setNext(head);
            }
        }

    }

    public OfficeFloor(Space... officeMas) {
        head = new Node();
        numberOfOffice = officeMas.length;
        head.setElement(officeMas[0]);
        if (numberOfOffice != 1) {
            Node lastOffice = head;

            for (int i = 1; i < numberOfOffice; i++) {
                Node node = new Node(officeMas[i]);
                lastOffice.setNext(node);
                lastOffice = node;
                if (i == numberOfOffice - 1) {

                    lastOffice.setNext(head);
                }
            }
        } else {
            head.setNext(head);
        }
    }

    private Node head;
    int numberOfOffice;

    public Node getOffice(int number) {
        Node office = new Node();
        if (number != 0) {
            office = head;
            for (int i = 0; i < number; i++) {
                office = office.getNext();
            }
            return office;
        } else {
            return head;
        }
    }

    private void addOffice(int number) {
        Node node = new Node();
        if (number != 0) {
            node.setNext(this.getOffice(number - 1).getNext());
            this.getOffice(number - 1).setNext(node);
        } else {
            node.setNext(head);
            head = node;
            this.getOffice(this.numberOfOffice - 1).setNext(node);
        }
        numberOfOffice++;
    }

    private void delOffice(int number) {

        if (number != 0) {
            this.getOffice(number - 1).setNext(this.getOffice(number).getNext());
        } else {

            this.getOffice(this.numberOfOffice - 1).setNext(head.getNext());// ������ ���� ��� ������ �������� ������� �� �� ����� ��������?
            head = head.getNext();
        }
        numberOfOffice--;
    }

    /* (non-Javadoc)
     * @see Building.Floor#getNumberOfOffice()
     */
    @Override
    public int getNumberOfSpaces() {
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
     * @see Building.Floor#getNumberRoomsOfFloor()
     */
    @Override
    public int getNumberRoomsOfSpaces() {
        if (head != null) {
            int number = 0;
            Node node = head;
            for (int i = 0; i < this.getNumberOfSpaces(); i++) {
                number += node.getElement().getAmtOfRoom();
                node = node.getNext();
            }
            return number;
        } else {
            return 0;
        }
    }

    /* (non-Javadoc)
     * @see Building.Floor#getTotalArea()
     */
    @Override
    public double getTotalArea() {
        if (head != null) {
            int area = 0;
            Node node = head;
            for (int i = 0; i < this.getNumberOfSpaces(); i++) {
                area += node.getElement().getArea();
                node = node.getNext();
            }
            return area;
        } else {
            return 0;
        }
    }


    /* (non-Javadoc)
     * @see Building.Floor#getMassOffice()
     */
    @Override
    public Space[] getSpacesArray() {
        Space[] ofMas = new Space[this.getNumberOfSpaces()];
        if (head != null) {
            ofMas[0] = head.getElement();
            Node node = head;
            for (int i = 1; i < this.getNumberOfSpaces(); i++) {
                node = node.getNext();
                ofMas[i] = node.getElement();
            }
            return ofMas;
        } else {
            return ofMas;
        }
    }

    /* (non-Javadoc)
     * @see Building.Floor#getOneOffice(int)
     */
    @Override
    public Space getSpaceByNum(int number) {
        if (number >= this.getNumberOfSpaces() || number < 0) {
            throw new SpaceIndexOutOfBoundsException(number);
        }
        return this.getOffice(number).getElement();
    }

    /* (non-Javadoc)
     * @see Building.Floor#changeOffice(int, Building.Space)
     */
    @Override
    public void changeSpace(int number, Space newSpace) {
        if (number >= this.getNumberOfSpaces() || number < 0) {
            throw new SpaceIndexOutOfBoundsException(number);
        }
        Node node = new Node(newSpace);
        node.setNext(this.getOffice(number).getNext());
        this.getOffice(number - 1).setNext(node);
    }

    /* (non-Javadoc)
     * @see Building.Floor#addNewOffice(int)
     */
    @Override
    public void addNewSpace(int number) {
        if (number >= this.getNumberOfSpaces() || number < 0) {
            throw new SpaceIndexOutOfBoundsException(number);
        }
        this.addNewSpace(number);
    }

    /* (non-Javadoc)
     * @see Building.Floor#deleteOffice(int)
     */
    @Override
    public void deleteSpace(int number) {
        if (number >= this.getNumberOfSpaces() || number < 0) {
            throw new SpaceIndexOutOfBoundsException(number);
        }
        this.delOffice(number);
    }

    /* (non-Javadoc)
     * @see Building.Floor#getBestSpace()
     */
    @Override
    public Space getBestSpace() {
        if (head != null) {
            double maxArea = head.getElement().getArea();
            Space maxAreaOffice = new Office();
            Node node = head;
            for (int i = 0; i < this.getNumberOfSpaces(); i++) {
                if (node.getElement().getArea() > maxArea) {
                    maxArea = node.getElement().getArea();
                    maxAreaOffice = node.getElement();
                }
                node = node.getNext();
            }
            return maxAreaOffice;
        } else {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see Building.Floor#addFitOffice(int, Building.Office)
     */
    @Override
    public void addFitSpace(int number, Space newSpace) {
        if (number >= this.getNumberOfSpaces() || number < 0) {
            throw new SpaceIndexOutOfBoundsException(number);
        }
        Node node = new Node(newSpace);
        if (number != 0) {
            node.setNext(this.getOffice(number - 1).getNext());
            this.getOffice(number - 1).setNext(node);
        } else {
            node.setNext(head);
            head = node;
            this.getOffice(this.numberOfOffice - 1).setNext(node);
        }
        numberOfOffice++;
    }

    @Override
    public Iterator iterator() {
        return new FloorIterator();
    }

    @Override
    public String toString() {
        StringBuilder stringFloor = new StringBuilder();
        stringFloor.append(this.getClass().getSimpleName());
        stringFloor.append(" (");
        for (int i = 0; i < getNumberOfSpaces()-1; i++) {
            stringFloor.append(getSpaceByNum(i));
            stringFloor.append(", ");
        }
        stringFloor.append(getSpaceByNum(getNumberOfSpaces()-1));
        return stringFloor.append(")").toString();
    }

    @Override
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
            for (int i = 0; i < this.getNumberOfSpaces(); i++) {
                ((OfficeFloor) obj).addFitSpace(i, (Space) this.getSpaceByNum(i).clone());
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
            code = (code ^ this.getSpaceByNum(i).hashCode());
        }
        return code;
    }

}
