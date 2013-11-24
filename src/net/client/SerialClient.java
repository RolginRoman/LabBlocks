/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.client;

import Building.Building;
import Building.Buildings;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author irolg_000
 */
public class SerialClient {
    public static void main(String[] args) throws IOException
    {
        String input = "Serialinput", types = "Serialtypes", rating = "Serialrating";
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
        ObjectOutputStream oos = new ObjectOutputStream(out);
        
        String type;
        Building br;
        
        try {
            while ((type = bufTypes.readLine()) != null) {
                try {
                    toSrv.writeUTF(type);
                    toSrv.flush();
                    //setFactory(type);
                    br = Buildings.readBuilding(bufInput);
                    oos.writeObject(br);
                    //Buildings.outputBuilding(br, out);
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
