package socketserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerA {

    public static void main(String[] args) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(2910);
        System.out.println("Server started..");
        Socket socketToClient = welcomeSocket.accept();
        System.out.println("Client connected..");

        InputStream inputStream = socketToClient.getInputStream();
        String read = read(inputStream);
        System.out.println("Received from client: " + read);

        OutputStream outputStream = socketToClient.getOutputStream();

        send(outputStream, "hello from server");
    }

    private static String read(InputStream inputStream) throws IOException {
        //translating input
        byte[] lenBytes = new byte[4];
        inputStream.read(lenBytes, 0, 4);
        int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
        byte[] receivedBytes = new byte[len];
        inputStream.read(receivedBytes, 0, len);

        String receivedFromClient = new String(receivedBytes, 0, len);

        return receivedFromClient;
    }

    private static void send(OutputStream outputStream,String toSend) throws IOException {
        byte[] toSendBytes = toSend.getBytes();
        int toSendLen = toSendBytes.length;
        byte[] toSendLenBytes = new byte[4];
        toSendLenBytes[0] = (byte) (toSendLen & 0xff);
        toSendLenBytes[1] = (byte) ((toSendLen >> 8) & 0xff);
        toSendLenBytes[2] = (byte) ((toSendLen >> 16) & 0xff);
        toSendLenBytes[3] = (byte) ((toSendLen >> 24) & 0xff);
        outputStream.write(toSendLenBytes);
        outputStream.write(toSendBytes);
    }
}
