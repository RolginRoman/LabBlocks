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
        int i=0;
        while (true) {
            try {
                System.out.print("Waiting for a client...");
                client = myServer.accept();
                Thread t = new Thread(new RaterRunnable(client));
                System.out.println((++i) + " Client connected");
                t.start();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
