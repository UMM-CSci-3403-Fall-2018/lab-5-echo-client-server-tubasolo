package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {

  // Set the port the server is going to listen on
  // We never want this to change so we set it as final
  public static final int portNumber = 6013;

  public static void main(String[] args){

    try{

      // Create the server socket that listens for requests from a client
      ServerSocket serverSocket = new ServerSocket(portNumber);

      // We loop infintely here since that's what servers do
      while(true) {

        // The code will actually just stay here until a client tries to connect
        // We also output a friendly message letting people know that a client has connected
        Socket client = serverSocket.accept();
        System.out.println("Recieved a request");

        // Create the input and output streams that we'll read and write from
        InputStream clientRequest = client.getInputStream();
        OutputStream serverResponse = client.getOutputStream();

        // Have a temporary int here to hold whatever byte we get from the client
        int tempByte;
        while((tempByte = clientRequest.read()) != -1) {
          // Once we get the byte, we immediately send it back
          serverResponse.write(tempByte);
        }

        // Shut down the connection with the client once it's finished talking to us
        client.shutdownOutput();

      }
    }
    // Some more exception catching
     catch(IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
