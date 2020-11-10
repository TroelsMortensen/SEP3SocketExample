package client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 2910);

        // Send to server
        OutputStream outputStream = socket.getOutputStream();
        String response = "Hello from client";
        byte[] responseAsBytes = response.getBytes();
        outputStream.write(responseAsBytes, 0, responseAsBytes.length);

        // read from client
        InputStream inputStream = socket.getInputStream();
        byte[] lenbytes = new byte[1024];
        int read = inputStream.read(lenbytes,0,lenbytes.length);
        String message = new String(lenbytes,0,read);
        System.out.println("Received from server: " + message);

        socket.close();
    }
}
