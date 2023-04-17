import java.rmi.Naming;


public class Client {
    public static void main (String args[]) {
        if (args.length != 1)
            throw new IllegalArgumentException ("Syntax: Client <hostname>");

        try {
            Server server = (Server) Naming.lookup
                    ("rmi:/" + "localhost" + "/news");
            String when = server.hallotest();
            System.out.println ("Test: " + server.hallotest());


        } catch (Exception e) {
            System.out.println("Client: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
