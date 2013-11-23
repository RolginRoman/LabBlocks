package Building;

import Building.dwelling.Dwelling;
import java.io.*;

import threads.Cleaner;
import threads.FloorSynchronizer;
import threads.Repairer;
import threads.SequentalCleaner;
import threads.SequentalRepairer;
import Building.dwelling.Flat;
import Building.office.Office;
import Building.office.OfficeBuilding;
import Building.office.OfficeFloor;
import threads.FloorSemaphore;

public class Main {

    public static void main(String[] s) throws IOException, ClassNotFoundException {
        try {
            Space o1 = new Office(1, 100);
            Space o2 = new Office(2, 200);
            Space o3 = new Flat(3, 300);
            Space o4 = new Flat(4, 400);
            Space o5 = new Office(5, 500);
            Space o6 = new Office(6, 600);
            Space o7 = new Office(7, 700);
            Floor of1 = new OfficeFloor(new Space[]{o1, o2, o3, o4});
            Floor of2 = new OfficeFloor(new Space[]{o5, o6, o7});
            Building ob = new OfficeBuilding(new Floor[]{of1, of2});
//            File file = new File("input.bin");
//
//            OutputStream out = new FileOutputStream(file);
//            Buildings.outputBuilding(ob, out);
//
//            InputStream in = new FileInputStream("input.bin");
//            Buildings.inputBuilding(in);
//
//            Writer outWr = new FileWriter("input.txt");
//            Buildings.writeBuilding(ob, outWr);
//
//            Reader inRead = new FileReader("input.txt");
//            Buildings.readBuilding(inRead);
//
//            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("input.bin"));
//            objOut.writeObject(ob);
//            objOut.close();
//
//            ObjectInputStream objIn = new ObjectInputStream(
//                    new FileInputStream("input.bin"));
//            Building ob2 = (OfficeBuilding) objIn.readObject();
//
//            if (ob.getNumberOfFloor() == ob2.getNumberOfFloor()) {
//                System.out.println("equals!");
//            }

            FloorSemaphore fs = new FloorSemaphore();
            SequentalRepairer sr = new SequentalRepairer(of1, fs);
            SequentalCleaner sc = new SequentalCleaner(of1, fs);
            Thread t1 = new Thread(sr);
            Thread t2 = new Thread(sc);
            t1.start();
            t2.start();
            
            Writer fos =new FileWriter("input.txt");
            
            Buildings.writeBuilding(ob, fos);
            System.out.println(ob.toString());
            Reader r = new FileReader("input.txt");
            
            Building readed = Buildings.readBuilding(r);

            System.out.println(readed.toString());
            
            Writer types =new FileWriter("types.txt");
            PrintWriter bw = new PrintWriter(types);
            bw.println("Dwelling");
            bw.flush();
            types.close();
        }
        catch (InvalidSpaceAreaException e) {
            System.out.println(e);
        }
        catch (InvalidRoomsCountException e) {
            System.out.println(e);

        }
    }
}
