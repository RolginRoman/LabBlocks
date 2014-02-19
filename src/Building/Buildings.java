package Building;

import java.io.*;

import Building.dwelling.DwellingFactory;
import MyException.WrongFormatReadFromFileException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buildings {

    private static BuildingFactory myFactory = new DwellingFactory();

    public static Floor getSynchronizedFloor(Floor floor) {
        return new SynchronizedFloor(floor);
    }

    public static void setBuildingFactory(BuildingFactory bf) {
        myFactory = bf;
    }

    public static Space createSpace(int roomCount, double area) {
        return myFactory.createSpace(roomCount, area);
    }

    public static Space createSpace(double area) {
        return myFactory.createSpace(area);
    }

//    public static Space createSpace(double area, Class spaceClass) throws IllegalArgumentException {
//        Constructor con = null;
//        try {
//            con = spaceClass.getConstructor(Double.TYPE);
//        } catch (NoSuchMethodException ex) {
//            throw new IllegalArgumentException();
//        }
//        try {
//            return (Space) con.newInstance(area);
//        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
//            throw new IllegalArgumentException();
//        }
//    }
    public static Space createSpace(double area, Class<? extends Space> spaceClass) throws IllegalArgumentException {
        Constructor<? extends Space> con = null;
        try {
            con = spaceClass.getConstructor(Double.TYPE);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException();
        }
        try {
            return con.newInstance(area);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalArgumentException();
        }
    }

    public static Floor createFloor(int spacesCount) {
        return myFactory.createFloor(spacesCount);
    }

    public static Floor createFloor(Class<? extends Floor> floorClass, Space... spaces) throws IllegalArgumentException {
        Constructor<? extends Floor> con = null;
        try {
            con = floorClass.getConstructor(Space[].class);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException();
        }
        try {
            return con.newInstance((Object) spaces);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalArgumentException();
        }
    }

    public static Floor createFloor(Space... spaceMas) {
        return myFactory.createFloor(spaceMas);
    }

    public static Building createBuild(int countFloor, int[] masFloor) {
        return myFactory.createBuilding(countFloor, masFloor);
    }

    public static Building createBuild(Floor... masFloor) {
        return myFactory.createBuilding(masFloor);
    }

    public static Building createBuild(Class<? extends Building> buildClass, Floor... masFloor) throws IllegalArgumentException {
        Constructor<? extends Building> con = null;
        try {
            con = buildClass.getDeclaredConstructor(Floor[].class);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException();
        }
        try {
            return con.newInstance((Object) masFloor);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalArgumentException();
        }
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream myOut = new DataOutputStream(out);
        myOut.writeInt(building.getFloorCount());
        for (int i = 0; i < building.getFloorCount(); i++) {
            myOut.writeInt(building.getFloorByNum(i).getNumberOfSpaces());
            for (int j = 0; j < building.getFloorByNum(i).getNumberOfSpaces(); j++) {
                myOut.writeInt(building.getFloorByNum(i).getSpaceByNum(j).getAmtOfRoom());
                myOut.writeDouble(building.getFloorByNum(i).getSpaceByNum(j).getArea());
            }
        }
        myOut.flush();
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream myIn = new DataInputStream(in);
        int numberFloor = myIn.readInt();
        Floor[] massFloor = new Floor[numberFloor];
        for (int i = 0; i < numberFloor; i++) {
            Space[] massSpace = new Space[myIn.readInt()];
            for (int j = 0; j < massSpace.length; j++) {
                Space sp = createSpace(myIn.readInt(), myIn.readDouble());
                massSpace[j] = sp;
            }
            massFloor[i] = createFloor(massSpace);
        }
        return createBuild(massFloor);
    }

    public static Building inputBuilding(InputStream in, Class buildingClass, Class floorClass, Class spaceClass) throws IOException {
        try (DataInputStream myIn = new DataInputStream(in)) {
            int numberFloor = myIn.readInt();
            Floor[] massFloor = new Floor[numberFloor];
            for (int i = 0; i < numberFloor; i++) {
                Space[] massSpace = new Space[myIn.readInt()];
                for (int j = 0; j < massSpace.length; j++) {
                    Space sp = createSpace(myIn.readDouble(), spaceClass);
                    massSpace[j] = sp;
                }
                massFloor[i] = createFloor(floorClass, massSpace);
            }
            return createBuild(buildingClass, massFloor);
        }
    }

    public static void writeBuilding(Building building, Writer out) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(out));
        pw.println(Integer.toString(building.getFloorCount()));
        for (int i = 0; i < building.getFloorCount(); i++) {

            pw.println(building.getFloorByNum(i).getNumberOfSpaces());
            for (int j = 0; j < building.getFloorByNum(i).getNumberOfSpaces(); j++) {
                pw.println(building.getFloorByNum(i).getSpaceByNum(j).getAmtOfRoom());
                pw.println(building.getFloorByNum(i).getSpaceByNum(j).getArea());
            }
        }
        pw.flush();
    }

    public static void writeBuildingInLines(Building building, Writer out) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(out));
        pw.print(Integer.toString(building.getFloorCount()));
        pw.print(" ");
        for (int i = 0; i < building.getFloorCount(); i++) {
            pw.print(building.getFloorByNum(i).getNumberOfSpaces());
            pw.print(" ");
            for (int j = 0; j < building.getFloorByNum(i).getNumberOfSpaces(); j++) {
                pw.print(building.getFloorByNum(i).getSpaceByNum(j).getAmtOfRoom());
                pw.print(" ");
                pw.print(building.getFloorByNum(i).getSpaceByNum(j).getArea());
                pw.print(" ");
            }
            pw.println();
        }
        pw.flush();
    }

    public static void writeBuildingF(Building building, Writer out) {
        PrintWriter pw = new PrintWriter(out, true);
        pw.printf("%d ", building.getFloorCount());
        for (int i = 0; i < building.getFloorCount(); i++) {
            pw.printf("%d ", building.getFloorByNum(i).getNumberOfSpaces());
            for (int j = 0; j < building.getFloorByNum(i).getNumberOfSpaces(); j++) {
                pw.printf("%d %.1f ", building.getFloorByNum(i).getSpaceByNum(j).getAmtOfRoom(), building.getFloorByNum(i).getSpaceByNum(j).getArea());
            }
        }

    }

    //    public static Building readBuilding(Reader in) throws IOException {
    //        BufferedReader myIn = new BufferedReader(in);
    //        int numberFloor = Integer.parseInt(myIn.readLine());
    //        Floor[] massFloor = new Floor[numberFloor];
    //        for (int i = 0; i < numberFloor; i++) {
    //            Space[] massSpace = new Space[Integer.parseInt(myIn.readLine())];
    //            for (int j = 0; j < massSpace.length; j++) {
    //                //Space sp = new Office(Integer.parseInt(myIn.readLine()), Double.parseDouble(myIn.readLine()));
    //                Space s = createSpace(Integer.parseInt(myIn.readLine()), Double.parseDouble(myIn.readLine()));
    //                massSpace[j] = s;
    //            }
    //            massFloor[i] = createFloor(massSpace);
    //            //new OfficeFloor(massSpace);
    //        }
    //        Building building = createBuild(massFloor);//new OfficeBuilding(massFloor);
    //        return building;
    //    }
    public static Building readBuildingF(Scanner s) {
        int floorCount = s.nextInt();
        Floor[] floors = new Floor[floorCount];
        for (int i = 0; i < floorCount; i++) {
            int spaceCount = s.nextInt();
            Space[] spaces = new Space[spaceCount];
            for (int j = 0; j < spaceCount; j++) {
                int roomCount = s.nextInt();
                double area = s.nextDouble();
                Space space = createSpace(roomCount, area);
                spaces[j] = space;
            }
            floors[i] = createFloor(spaces);
        }
        return createBuild(floors);
    }

    public static Building readBuilding(Reader in)
            throws IOException, WrongFormatReadFromFileException {

        StreamTokenizer st = new StreamTokenizer(in);
        do {
            st.nextToken();
            if (st.ttype == StreamTokenizer.TT_EOF) {
                throw new WrongFormatReadFromFileException();
            }
        } while (st.ttype != StreamTokenizer.TT_NUMBER);
        Floor[] massFloor = new Floor[(int) st.nval];
        for (int i = 0; i < massFloor.length; i++) {
            do {
                st.nextToken();
                if (st.ttype == StreamTokenizer.TT_EOF) {
                    throw new WrongFormatReadFromFileException();
                }
            } while (st.ttype != StreamTokenizer.TT_NUMBER);
            Space[] massSpace = new Space[(int) st.nval];
            int numOfRooms;
            double square;
            for (int j = 0; j < massSpace.length; j++) {
                do {
                    st.nextToken();
                    if (st.ttype == StreamTokenizer.TT_EOF) {
                        throw new WrongFormatReadFromFileException();
                    }
                } while (st.ttype != StreamTokenizer.TT_NUMBER);
                numOfRooms = (int) st.nval;
                do {
                    st.nextToken();
                    if (st.ttype == StreamTokenizer.TT_EOF) {
                        throw new WrongFormatReadFromFileException();
                    }
                } while (st.ttype != StreamTokenizer.TT_NUMBER);
                square = st.nval;
                Space s = createSpace(numOfRooms, square);
                massSpace[j] = s;
            }
            massFloor[i] = createFloor(massSpace);
        }
        return createBuild(massFloor);
    }
}
