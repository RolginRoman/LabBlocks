package Building;

import Forms.MainForm;
import Building.dwelling.Dwelling;
import Building.dwelling.DwellingFloor;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;
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
            Floor of1 = new OfficeFloor(new Space[]{o1, o2, o3, o4, o5, o6, o7});
            Floor of2 = new OfficeFloor(new Space[]{o5, o6, o7, o5, o6, o7, o7, o5, o6, o7, o7, o5, o5, o6, o7});
            Floor of3 = new OfficeFloor(new Space[]{o1, o2, o3, o4, o5, o4, o5, o6, o7});
            Floor of4 = new OfficeFloor(new Space[]{o1, o2, o3, o2, o1, o2, o3, o4, o5, o6, o7});
            Floor fff = new DwellingFloor(5);
            Building ob = new Dwelling(new Floor[]{of1, of2, of3, of4});
//            of1.deleteSpace(500);
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
//            if (ob.getFloorCount() == ob2.getFloorCount()) {
//                System.out.println("equals!");
//            }

            FloorSemaphore fs = new FloorSemaphore();
            SequentalRepairer sr = new SequentalRepairer(of1, fs);
            SequentalCleaner sc = new SequentalCleaner(of1, fs);
            Thread t1 = new Thread(sr);
            Thread t2 = new Thread(sc);
//            t1.start();
//            t2.start();

            Writer fos = new FileWriter("input.txt");

            Buildings.writeBuildingF(ob, fos);

            fos.close();
            //System.out.println(ob.toString());
            Reader r = new FileReader("input.txt");
            Building readed = Buildings.readBuildingF(new Scanner(r));

            r.close();

            System.out.println(readed.toString());
//            Writer types = new FileWriter("types.txt");
//            PrintWriter bw = new PrintWriter(types);
//            bw.println("Dwelling");
//            bw.println("OfficeBuilding");
//            bw.println("Hotel");
//            bw.println("Dwelling");
//            bw.println("Hotel");
//            bw.println("OfficeBuilding");
//            bw.flush();
//            types.close();
//            Writer fos1 = new FileWriter("Serialinput.txt");
//
//            Buildings.writeBuildingInLines(ob, fos1);
//            Buildings.writeBuildingInLines(ob, fos1);
//            Buildings.writeBuildingInLines(ob, fos1);
//            Buildings.writeBuildingInLines(ob, fos1);
//            Buildings.writeBuildingInLines(ob, fos1);
//            Buildings.writeBuildingInLines(ob, fos1);
//            fos1.close();
//            Writer types1 = new FileWriter("Serialtypes.txt");
//            PrintWriter bw1 = new PrintWriter(types1);
//            bw1.println("Dwelling");
//            bw1.println("OfficeBuilding");
//            bw1.println("Hotel");
//            bw1.println("Dwelling");
//            bw1.println("Hotel");
//            bw1.println("OfficeBuilding");
//            bw1.flush();
//            types1.close();
//            MainForm mf = new MainForm();
//            mf.setLocation(200, 300);
//            mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            mf.setVisible(true);
//            mf.setResizable(false);
        }
        catch (InvalidSpaceAreaException e) {
            System.out.println(e);
        }
        catch (InvalidRoomsCountException e) {
            System.out.println(e);

        }
    }
}
