
import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject  implements Server{

    public ServerImpl () throws RemoteException {
    }



    //public Newspaper addArticle() throws RemoteException{
        //System.out.println("Test");
        //return new Newspaper();
    //}
    public String  hallotest() throws RemoteException{
        return "test435";

    }

    public static void main (String args[]) {
        try {
            ServerImpl newspaper1 = new ServerImpl();
            Naming.rebind("news", newspaper1);
            System.out.println("The server is up");

        } catch (Exception e) {
            System.out.println("ServerImpl42442: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
