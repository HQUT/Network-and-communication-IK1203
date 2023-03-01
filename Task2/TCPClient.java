package tcpclient;
import java.net.*;
import java.io.*;


public class TCPClient {


    public TCPClient() {
    }

    boolean shutdown;
    Integer timeout;
    Integer limit;

    public TCPClient(boolean shutdown, Integer timeout, Integer limit) {
        this.shutdown = shutdown;
        this.timeout = timeout;
        this.limit = limit;
    }


    public byte[] askServer(String hostname, int port, String toServerBytes) throws IOException {

        ByteArrayOutputStream FromServer = new ByteArrayOutputStream();
        Socket Socket = new Socket(hostname, port);
        try {
        int BUFFERSIZE = 1024;
        byte[] ServerBuffer = new byte[BUFFERSIZE];
        if (limit != null) {
            ServerBuffer = new byte[limit];
        }


            Socket.getOutputStream().write(toServerBytes);


            if (shutdown) {
                Socket.shutdownOutput();
                return toServerBytes;
            }
            if (timeout != null) {
                Socket.setSoTimeout(timeout);
            }


            int Serverlength;

            if(limit != null) {
                while ((Serverlength = Socket.getInputStream().read(ServerBuffer)) != -1) {
                        FromServer.write(ServerBuffer, 0, limit);

                    if (limit <= Serverlength ) {
                       break;
                    }

                }
            }

            if (limit == null){
                while ((Serverlength = Socket.getInputStream().read(ServerBuffer)) != -1)
                    FromServer.write(ServerBuffer, 0, Serverlength);
            }

        }
        catch (SocketTimeoutException ex) {
        }


        Socket.close();
        return FromServer.toByteArray();
    }
}




