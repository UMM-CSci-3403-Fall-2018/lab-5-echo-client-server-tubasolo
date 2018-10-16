package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {

  public static final int portNumber = 6013;

  public static void main(String[] args) {
    String server;

    if(args.length == 0) {
      server = "127.0.0.1";
    } else {
      server = args[0];
    }

    try {
      Socket socket = new Socket(server, portNumber);
      OutputStream userIn = socket.getOutputStream();
      InputStream response = socket.getInputStream();

      int systemIn;
      while((systemIn = System.in.read()) != -1) {
        userIn.write(systemIn);
        int serverResponse = response.read();
        System.out.write(serverResponse);
      }

      socket.shutdownOutput();
      System.out.flush();
    } catch (ConnectException ce) {
      System.out.println("We were unable to connect to " + server);
      System.out.println("You should make sure the server is running.");
    } catch (IOException ioe)  {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);

    }

  }

}
