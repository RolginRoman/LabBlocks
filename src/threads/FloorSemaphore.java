/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

/**
 *
 * @author irolg_000
 */
public class FloorSemaphore {

    private Object lock = new Object();
    private boolean repairing = false;

    public void beginRepair() {
        synchronized (lock) {
            while (repairing) {
                try {
                    lock.wait();
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            repairing = false;
            lock.notifyAll();
        }
    }

    public void endRepair() {
        repairing = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void beginClean() {
        synchronized (lock) {
            while (!repairing) {
                try {
                    lock.wait();
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            repairing = true;
            lock.notifyAll();
        }
    }

    public void endClean() {
        repairing = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

}
