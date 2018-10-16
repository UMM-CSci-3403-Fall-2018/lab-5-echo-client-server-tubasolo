package echoserver;

import java.io.*;
import java.net.*;
import java.util.*;

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
      InputStream response = socket.getInputStream();
      OutputStream userIn = socket.getOutputStream();

      byte systemInByte;
      while((systemInByte = System.in.read()) != -1) {
        userIn.write(systemInByte);
        byte serverResponseByte = response.read();
        System.out.println(serverResponseByte);
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
