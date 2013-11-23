package threads;

import Building.Floor;

public class SequentalCleaner implements Runnable {

    private Floor floor;
    private FloorSemaphore fs;

    public SequentalCleaner(Floor floor, FloorSemaphore fs) {
        this.floor = floor;
        this.fs = fs;
    }

    @Override
    public void run() {

        for (int i = 0; i < floor.getNumberOfSpaces(); i++) {
            fs.beginClean();
            System.out.println(String.format("Cleaned: Space #%d with space %.2f", i, floor.getOneSpace(i).getArea()));
            fs.endClean();
        }

    }

}
