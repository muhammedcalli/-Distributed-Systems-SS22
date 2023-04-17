import javax.swing.text.Position;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Locale;

public class TCPServer {
  public static void main (String args[]) {
      Newspaper newspaper1 = new Newspaper("newspaper1");
      Newspaper newspaper2 = new Newspaper("newspaper2");
      Newspaper newspaper3 = new Newspaper("newspaper3");

    try{
      System.out.println("The Server is running");
	  int serverPort = 4242;
	  ServerSocket listenSocket = new ServerSocket (serverPort);
	  while(true) {
	    Socket clientSocket = listenSocket.accept();
	    System. out.println("New Connection");
	    Connection connection = new Connection(clientSocket, newspaper1, newspaper2, newspaper3);


	  }
    } catch( IOException e){
        e.printStackTrace();
    }
    //{System.out.println(" Listen :"+ e.getMessage());}
  }// main
}//class


class Connection extends Thread {
  ObjectInputStream in;
  ObjectOutputStream out;
  Socket clientSocket;
  Newspaper newspaper1;
  Newspaper newspaper2;
  Newspaper newspaper3;

  public Connection (Socket aClientSocket, Newspaper newspaper1, Newspaper newspaper2, Newspaper newspaper3) {
      this.newspaper1 = newspaper1;
      this.newspaper2 = newspaper2;
      this.newspaper3 = newspaper3;

    try {
      clientSocket = aClientSocket;
      out = new ObjectOutputStream ( clientSocket.getOutputStream() );
      in = new ObjectInputStream ( clientSocket.getInputStream() );
      this.start();
    } catch( IOException e) {System. out. println(" Connection:"+ e.getMessage());}
  }

  public void run() {

      try {
              while (true) {

                  Message receivedMessage = (Message) in.readObject();


                  if (receivedMessage.getMethod().equals("add")) {
                      switch (receivedMessage.getNewspaperselection()) {
                          case 1: {
                              newspaper1.addArticle(receivedMessage.getArticle(), receivedMessage.getCategory(), Integer.parseInt(String.valueOf(receivedMessage.getPageofNumber())));
                              out.writeObject("added article to newspaper1 with name: " + receivedMessage + "Category:" + receivedMessage.getCategory() + "Pagenumber:" + receivedMessage.getPageofNumber());
                              break;
                          }
                          case 2: {
                              newspaper2.addArticle(receivedMessage.getArticle(), receivedMessage.getCategory(), Integer.parseInt(String.valueOf(receivedMessage.getPageofNumber())));
                              out.writeObject("added article to newspaper1 with name: " + receivedMessage + "Category:" + receivedMessage.getCategory() + "Pagenumber:" + receivedMessage.getPageofNumber());
                              break;
                          }
                          case 3: {
                              newspaper3.addArticle(receivedMessage.getArticle(), receivedMessage.getCategory(), Integer.parseInt(String.valueOf(receivedMessage.getPageofNumber())));
                              out.writeObject("added article to newspaper1 with name: " + receivedMessage + "Category:" + receivedMessage.getCategory() + "Pagenumber:" + receivedMessage.getPageofNumber());
                              break;
                          }
                          default:
                              out.writeObject("error, no such newspaper");
                              break;
                      }
                  }
                  if (receivedMessage.getMethod().equals("get")) {
                      switch (receivedMessage.getNewspaperselection()) {
                          case 1: {
                              List<String> names = newspaper1.getNames();
                              out.writeObject("The newspaper1 has article with the following names:\n" + names);
                              break;
                          }
                          case 2: {
                              List<String> names = newspaper2.getNames();
                              out.writeObject("The newspaper2 has article with the following names:\n" + names);
                              break;
                          }
                          case 3: {
                              List<String> names = newspaper3.getNames();
                              out.writeObject("The newspaper3 has article with the following names:\n" + names);
                              break;
                          }
                          default:
                              out.writeObject("error, no such newspaper!");
                              break;
                      }
                  } else
                      out.writeObject("error, unknown method!");
                  System.out.println("Sent message of Newspaper:" + receivedMessage.getObject());
              }



          //System.out.println("Sent data: " + data);
      } catch (EOFException e) {
          System.out.println(" EOF:" + e.getMessage());
      } catch (IOException e) {
          System.out.println(" IO:" + e.getMessage());
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
  }
}



