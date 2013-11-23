package threads;

import Building.Floor;

public class SequentalRepairer implements Runnable {

    private Floor floor;
    private FloorSemaphore fs;

    public SequentalRepairer(Floor floor, FloorSemaphore fs) {
        this.floor = floor;
        this.fs = fs;
    }

    @Override
    public void run() {

        for (int i = 0; i < floor.getNumberOfSpaces(); i++) {
            fs.beginRepair();
            System.out.println(String.format("Repaired: Space #%d with space %.2f", i, floor.getOneSpace(i).getArea()));
            fs.endRepair();
        }
    }
}
