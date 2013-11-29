/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.server.parallel;

import Building.Building;
import Building.Buildings;
import Building.dwelling.DwellingFactory;
import Building.dwelling.hotel.HotelFactory;
import Building.office.OfficeFactory;
import MyException.BuildingUnderArrestException;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author Student
 */
public class SerialRaterRunnable implements Runnable {

    static final double[] DEFAULT_RATES = new double[]{1000, 1500, 2000};
    private Socket client;

    public SerialRaterRunnable(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out;
            InputStream in;
            out = new ObjectOutputStream(client.getOutputStream());
            in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String type;
            double rate;
            System.out.println("Wait for messages");
            out.writeUTF("You have been connected to server.");
            Building b = null;
            try {
                while ((type = ois.readUTF()) != null) {
                    //b = Buildings.inputBuilding(in);
                    try {
                        b = (Building) ois.readObject();
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Class Building broken");
                    }
                    System.out.println("Server gets building\n" + b);
                    try {
                        rate = getRating(b);
                        out.writeObject(Double.toString(rate));
                        //out.writeUTF(Double.toString(rate));
                        System.out.println(rate);
                    } catch (BuildingUnderArrestException ex) {
                        out.writeObject(ex);
                    }
                }
            } catch (EOFException ex) {
                System.out.println("EOFEx: " + ex.getMessage());
            } finally {
                out.close();
                in.close();
                client.close();
            }
        } catch (IOException ex) {
            System.out.println("Something wrong in " + Thread.currentThread());
        }
    }

    static private double getRating(Building b) throws BuildingUnderArrestException {
        Random r = new Random();
        if (r.nextDouble() < 0.1) {
            System.out.println("This building has been arrested");
            throw new BuildingUnderArrestException();
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
