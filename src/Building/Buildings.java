package Building;

import java.io.*;

import Building.dwelling.DwellingFactory;

public class Buildings {

    private static BuildingFactory myFactory = new DwellingFactory();

    public static Floor synchronizedFloor(Floor floor) {
        Floor sf = new SynchronizedFloor(floor);
        return sf;
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

    public static Floor createFloor(int spacesCount) {
        return myFactory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaceMas) {
        return myFactory.createFloor(spaceMas);
    }

    public static Building createBuild(int countFloor, int[] masFloor) {
        return myFactory.createBuilding(countFloor, masFloor);
    }

    public static Building createBuild(Floor[] masFloor) {
        return myFactory.createBuilding(masFloor);
    }

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream myOut = new DataOutputStream(out);
        myOut.writeInt(building.getNumberOfFloor());
        for (int i = 0; i < building.getNumberOfFloor(); i++) {

            myOut.writeInt(building.getOneFloor(i).getNumberOfSpaces());
            for (int j = 0; j < building.getOneFloor(i).getNumberOfSpaces(); j++) {

                myOut.writeInt(building.getOneFloor(i).getOneSpace(j).getAmtOfRoom());
                myOut.writeDouble(building.getOneFloor(i).getOneSpace(j).getArea());
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

    public static void writeBuilding(Building building, Writer out) {
        PrintWriter pw = new PrintWriter(new BufferedWriter(out));
        pw.println(Integer.toString(building.getNumberOfFloor()));
        for (int i = 0; i < building.getNumberOfFloor(); i++) {

            pw.println(building.getOneFloor(i).getNumberOfSpaces());
            for (int j = 0; j < building.getOneFloor(i).getNumberOfSpaces(); j++) {
                pw.println(building.getOneFloor(i).getOneSpace(j).getAmtOfRoom());
                pw.println(building.getOneFloor(i).getOneSpace(j).getArea());
            }
        }
        pw.flush();
    }

    public static Building readBuilding(Reader in) throws IOException {
        BufferedReader myIn = new BufferedReader(in);
        int numberFloor = Integer.parseInt(myIn.readLine());
        Floor[] massFloor = new Floor[numberFloor];
        for (int i = 0; i < numberFloor; i++) {
            Space[] massSpace = new Space[Integer.parseInt(myIn.readLine())];
            for (int j = 0; j < massSpace.length; j++) {
                //Space sp = new Office(Integer.parseInt(myIn.readLine()), Double.parseDouble(myIn.readLine()));
                Space s = createSpace(Integer.parseInt(myIn.readLine()), Double.parseDouble(myIn.readLine()));
                massSpace[j] = s;
            }
            massFloor[i] = createFloor(massSpace);
                    //new OfficeFloor(massSpace);
        }

        Building building = createBuild(massFloor);//new OfficeBuilding(massFloor);
        return building;
    }
}
