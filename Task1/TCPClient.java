package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {

    public TCPClient() {
    }

    public byte[] askServer(String hostname, int port, byte[] toServerBytes) throws IOException {

            int Serverlength;
            ByteArrayOutputStream FromServer = new ByteArrayOutputStream();
            byte[] ServerBuffer = new byte[1024];
            Socket Socket = new Socket(hostname, port);
            Socket.getOutputStream().write(toServerBytes);

            while((Serverlength=Socket.getInputStream().read(ServerBuffer)) != -1) {
                FromServer.write(ServerBuffer,0,Serverlength);
            }
            Socket.close();
            return FromServer.toByteArray();
        }
    }


