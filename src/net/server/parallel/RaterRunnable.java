/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.server.parallel;

import Building.Building;
import Building.Buildings;
import Building.dwelling.DwellingFactory;
import Building.dwelling.hotel.HotelFactory;
import Building.office.OfficeFactory;
import MyException.BuildingUnderArrestException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author irolg_000
 */
public class RaterRunnable implements Runnable {

    static final double[] DEFAULT_RATES = new double[]{1000, 1500, 2000};
    private Socket client;
    private final Object lock = new Object();

    public RaterRunnable(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out;
            InputStream in;
            out = new DataOutputStream(client.getOutputStream());
            in = client.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            String type;
            double rate;
            System.out.println("Wait for messages");
            out.writeUTF("You have been connected to server.");
            Building b;
            try {
                while ((type = dis.readUTF()) != null) {
                    synchronized (lock) {
                        setFactory(type);
                        b = Buildings.inputBuilding(in);
                    }
                    System.out.println("Server gets building\n" + b);
                    try {
                        rate = getRating(b);
                        out.writeUTF(Double.toString(rate));
                        System.out.println(rate);
                    }
                    catch (BuildingUnderArrestException ex) {
                        out.writeUTF("Building under Arrest");
                    }
                }
            }
            catch (EOFException ex) {
                System.out.println("EOFEx: " + ex.getMessage());
            } finally {
                out.close();
                in.close();
                client.close();
            }
        }
        catch (IOException ex) {
            System.out.println("Something wrong in " + Thread.currentThread());
        }
    }

    static private double getRating(Building b) throws BuildingUnderArrestException {
        Random r = new Random();
        if (r.nextDouble() < 0.5) {
            System.out.println("This building has been arrested");
            throw new BuildingUnderArrestException();
        }
        double rate = b.getAllAreaOfBuilding();
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
