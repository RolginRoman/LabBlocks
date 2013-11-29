package net.server.sequental;

import Building.Building;
import Building.Buildings;
import Building.dwelling.DwellingFactory;
import Building.dwelling.hotel.HotelFactory;
import Building.office.OfficeFactory;
import MyException.BuildingUnderArrestException;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SerialServer {

    static final double[] DEFAULT_RATES = new double[]{1000, 1500, 2000};

    public static void main(String[] s) throws IOException {
        ServerSocket myServer = null;
        Socket client = null;
        ObjectOutputStream out = null;
        InputStream in = null;
        try {
            myServer = new ServerSocket(4444);
        }
        catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }
        try {
            System.out.print("Waiting for a client...");
            client = myServer.accept();
            System.out.println("Client connected");
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //out = new DataOutputStream(client.getOutputStream());
        out = new ObjectOutputStream(client.getOutputStream());
        //in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        in = client.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        String input = "", output, type;
        double rate = 0;
        System.out.println("Wait for messages");
        DataInputStream dis = new DataInputStream(in);
        Building b=null;
        try {
            while ((type = dis.readUTF()) != null) {
                //b = Buildings.inputBuilding(in);
                try {
                    b = (Building)ois.readObject();
                }
                catch (ClassNotFoundException ex) {
                    System.out.println("Class Building broken");
                }
                System.out.println("Server gets building\n" + b);
                try {
                    rate = getRating(b);
                    out.writeObject(Double.toString(rate));
                    //out.writeUTF(Double.toString(rate));
                    System.out.println(rate);
                }
                catch (BuildingUnderArrestException ex) {
                    out.writeObject(ex);
                }
            }
        }
        catch (EOFException ex) {
            System.out.println("EOFEx: " + ex.getMessage());
        } finally {
            out.close();
            in.close();
            client.close();
            myServer.close();
        }
    }

    static private double getRating(Building b) throws BuildingUnderArrestException {
        Random r = new Random();
        if (r.nextDouble() < 0.8) {
            System.out.println("This building has been arrested");
            throw new BuildingUnderArrestException("Building arrested");
        }
        double rate = b.getTotalArea();
        switch (b.getClass().getSimpleName()) {
            case "Dwelling": {
                System.out.println("Server gets dwelling building");
                rate *= DEFAULT_RATES[0];
                break;
            }
            case "OfficeBuilding": {
                System.out.println("Server gets office building");
                rate *= DEFAULT_RATES[1];
                break;
            }
            case "Hotel": {
                System.out.println("Server gets hotel building");
                rate *= DEFAULT_RATES[2];
                break;
            }
        }
        return rate;
    }

    private static void setFactory(String type) {
        switch (type) {
            case "Dwelling": {
                Buildings.setBuildingFactory(new DwellingFactory());
                break;
            }
            case "OfficeBuilding": {
                Buildings.setBuildingFactory(new OfficeFactory());
                break;
            }
            case "Hotel": {
                Buildings.setBuildingFactory(new HotelFactory());
                break;
            }
        }
    }
}
