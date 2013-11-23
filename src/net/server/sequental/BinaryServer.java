package net.server.sequental;

import Building.Building;
import Building.Buildings;
import MyException.BuildingUnderArrestException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class BinaryServer {

    static final double[] DEFAULT_RATES = new double[]{1000, 1500, 2000};

    public static void main(String[] s) throws IOException {
        ServerSocket myServer = null;
        Socket client = null;
        DataOutputStream out = null;
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
        out = new DataOutputStream(client.getOutputStream());
        //in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        in = client.getInputStream();
        String input = "", output;
        double rate = 0;
        System.out.println("Wait for messages");

        Building b = Buildings.inputBuilding(in);
        rate = getRating(b);
        out.writeDouble(rate);
        System.out.println(rate);

        out.close();
        in.close();
        client.close();
        myServer.close();
    }

    static private double getRating(Building b) throws BuildingUnderArrestException {
        Random r = new Random();
        if (r.nextDouble() < 0.1) {
            throw new BuildingUnderArrestException();
        }
        double rate = b.getAllAreaOfBuilding();
        switch (b.getClass().getSimpleName()) {
            case "Dwelling": {
                rate *= DEFAULT_RATES[0];
                break;
            }
            case "OfficeBuilding": {
                rate *= DEFAULT_RATES[1];
                break;
            }
            case "Hotel": {
                rate *= DEFAULT_RATES[2];
                break;
            }
        }
        return rate;
    }

}
