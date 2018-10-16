package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {

  // Set the port number we expect to connect to as a final variable
  // If we can't connect to that port we assume the server is down
  public static final int portNumber = 6013;

  public static void main(String[] args) {

    // Set the server ip/domain we want to connect to.
    // If the user doesn't specify an ip/domain we just assume their connecting on localhost
    String server;

    if(args.length == 0) {
      server = "127.0.0.1";
    } else {
      server = args[0];
    }

    // Try to connect to the server
    try {

      // Create a new socket and InputStream/OutputStream based on that socket
      Socket socket = new Socket(server, portNumber);
      OutputStream userIn = socket.getOutputStream();
      InputStream response = socket.getInputStream();

      // Read from stdin, send it to the server, and then write to stdout whatever the server sends back
      int systemIn;
      while((systemIn = System.in.read()) != -1) {
        userIn.write(systemIn);
        int serverResponse = response.read();
        System.out.write(serverResponse);
      }

      // Some cleanup so that hopefully nothing horrible happens
      socket.shutdownOutput();
      System.out.flush();
    }

    // Some exception catching so we at least have some idea of what went wrong
      catch (ConnectException ce) {
      System.out.println("We were unable to connect to " + server);
      System.out.println("You should make sure the server is running.");
    } catch (IOException ioe)  {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);

    }

  }

}
