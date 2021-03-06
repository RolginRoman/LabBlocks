package net.client;

import Building.Building;
import Building.Buildings;
import Building.dwelling.DwellingFactory;
import Building.dwelling.hotel.HotelFactory;
import Building.office.OfficeFactory;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class BinaryClient {

    public static void main(String[] args) throws IOException {
        String input = "input.txt", types = "types.txt", rating = "rating.txt";
        if (args.length == 3) {
            input = args[0];
            types = args[1];
            rating = args[2];
        }

        Socket mySocket = null;
        OutputStream out = null;
        DataInputStream in = null;
        try {
            mySocket = new Socket("localhost", 4444);
            out = mySocket.getOutputStream();
            //in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            in = new DataInputStream(mySocket.getInputStream());
        }
        catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
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

        String type;
        Building br;
        System.out.println(in.readUTF());
        try {
            while ((type = bufTypes.readLine()) != null) {
                try {
                    toSrv.writeUTF(type);
                    toSrv.flush();
                    //setFactory(type);
                    br = Buildings.readBuilding(bufInput);
                    Buildings.outputBuilding(br, out);
                    String rate = in.readUTF();
                    System.out.println(rate);
                    rateWriter.println(rate);
                    rateWriter.flush();
                }
                catch (IOException e) {
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

}
