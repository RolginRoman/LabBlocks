/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.client;

import Building.Building;
import Building.Buildings;
import Building.dwelling.DwellingFactory;
import Building.dwelling.hotel.HotelFactory;
import Building.office.OfficeFactory;
import MyException.BuildingUnderArrestException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irolg_000
 */
public class SerialClient {

    public static void main(String[] args) throws IOException {
        String input = "Serialinput", types = "Serialtypes", rating = "Serialrating";
        if (args.length == 3) {
            input = args[0];
            types = args[1];
            rating = args[2];
        }

        Socket mySocket = null;
        OutputStream out = null;
        ObjectInputStream in = null;
        try {
            mySocket = new Socket("localhost", 4444);
            out = mySocket.getOutputStream();
            //in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            in = new ObjectInputStream(mySocket.getInputStream());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        Reader inputFile = new FileReader(input);
        BufferedReader bufInput = new BufferedReader(inputFile);
        Reader typesFile = new FileReader(types);
        BufferedReader bufTypes = new BufferedReader(typesFile);
        Writer ratingFile = new FileWriter(rating);
        PrintWriter rateWriter = new PrintWriter(ratingFile);
        DataOutputStream toSrv = new DataOutputStream(out);
        ObjectOutputStream oos = new ObjectOutputStream(out);

        String type;
        Building br;

        try {
            while ((type = bufTypes.readLine()) != null) {
                try {
                    toSrv.writeUTF(type);
                    toSrv.flush();
                    setFactory(type);
                    br = Buildings.readBuilding(bufInput);
                    oos.writeObject(br);
                    //Buildings.outputBuilding(br, out);
                    Object rate = null;
                    try {
                        rate = in.readObject();
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println(rate);
                    if (rate instanceof BuildingUnderArrestException) {
                        rateWriter.println("Building is under arrest");
                    } else {
                        rateWriter.println(rate);
                    }
                    rateWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            out.close();
            in.close();
            stdIn.close();
            mySocket.close();
        }
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
