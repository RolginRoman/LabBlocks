package net.client;

import Building.Building;
import Building.Buildings;
import Building.dwelling.DwellingFactory;
import Building.dwelling.hotel.HotelFactory;
import Building.office.OfficeFactory;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
        String input = "", types = "", rating = "";
        if (args.length == 3) {
            input = args[0];
            types = args[1];
            rating = args[2];
            System.out.println(input);
            System.out.println(types);
            System.out.println(rating);
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
        Reader typesFile = new FileReader(types);
        BufferedReader bufR = new BufferedReader(typesFile);
        Writer ratingFile = new FileWriter(rating);
        PrintWriter rateWriter = new PrintWriter(ratingFile);

        String type;
        Building br;
        while ((type = bufR.readLine()) != null) {
            try {
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
                br = Buildings.readBuilding(inputFile);
                Buildings.outputBuilding(br, out);
                double rate = in.readDouble();
                System.out.println(rate);
                rateWriter.println(rate);
                rateWriter.flush();
                //out.writeUTF(br.toString());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        out.close();
        in.close();
        stdIn.close();
        mySocket.close();

    }

}
