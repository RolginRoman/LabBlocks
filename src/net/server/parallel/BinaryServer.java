package net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static void main(String[] s) throws IOException {
        ServerSocket myServer = null;
        Socket client = null;
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
