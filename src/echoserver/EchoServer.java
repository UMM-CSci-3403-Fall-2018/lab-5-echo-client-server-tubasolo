package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {

  public static final int portNumber = 6013;

  public static void main(String[] args){

    try{

      ServerSocket serverSocket = new ServerSocket(portNumber);

      while(true) {

        Socket client = serverSocket.accept();
        System.out.println("Recieved a request");

        InputStream clientRequest = client.getInputStream();
        OutputStream serverResponse = client.getOutputStream();

        int tempByte;
        while((tempByte = clientRequest.read()) != -1) {
          serverResponse.write(tempByte);
        }

        client.shutdownOutput();

      }
    } catch(IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
