package threads;

import Building.Floor;

public class FloorSynchronizer {

    private Floor floor;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public FloorSynchronizer(Floor floor) {
        this.floor = floor;
    }

    public void cleaning(int val) throws InterruptedException {

        synchronized (lock) {
            if (!canCleaning()) {
                throw new InterruptedException(); ///!!!!!!!!!!!!
            }
            while (!set) {
                lock.wait();
            }

            System.out.println("Cleaning room number  " + val + " with total area " + floor.getOneSpace(val).getArea() + " square meters ");
            set = false;
            lock.notifyAll();
        }
        //return val;++
    }

    public void repairing(int val) throws InterruptedException {
        synchronized (lock) {
            if (!canRepairing()) {
                throw new InterruptedException();
            }
            while (set) {
                lock.wait();
            }

            System.out.println("Repairing space number " + val + " with total area " + floor.getOneSpace(val).getArea() + " square meters ");
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canCleaning() {
        return current < floor.getNumberOfSpaces();
    }

    public boolean canRepairing() {
        return (!set && current < floor.getNumberOfSpaces()) || (set && current < floor.getNumberOfSpaces() - 1);
    }

    public Floor getFloor() {
        return floor;
    }

}
