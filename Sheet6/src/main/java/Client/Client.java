package Client;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import java.util.Scanner;

public class Client {
    public static String newspapaername;

    public static void main(String argv[]) throws Exception {

        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");

        //ConnectionVerfahren
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        Context context = new InitialContext(properties);
        TopicConnectionFactory connFactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
        TopicConnection conn = connFactory.createTopicConnection();
        TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic1 = (Topic) context.lookup("dynamicTopics/topic1");
        Topic topic2 = (Topic) context.lookup("dynamicTopics/topic2");

        TopicSubscriber subscriber = session.createSubscriber(topic1);
        TopicPublisher publisher = session.createPublisher(topic2);

        //////////////////////////////////////////////////////////////
        TextMessage textMessage = session.createTextMessage();
        System.out.println("All Newspapaer: ");
        textMessage.setText("Start");
        publisher.send(textMessage);
        conn.start();

        publisher.close();



        /////////////////////////////////////////////////////////////////

        //Receive the Newspapaernames

        TextMessage receiveMsg = (TextMessage)subscriber.receive();
        System.out.println("Receive: "+receiveMsg.getText());

        //Choose a newspapaer witch will be edited!
        System.out.println("Witch Newspaper?");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();
        subscriber.close();

        if(eingabe.equals("News2")||eingabe.contains("News1")) {
            newspapaername = eingabe;//Set the variable newspapaername

            //Send the options for Newspapaer 1
            textMessage = session.createTextMessage();
            textMessage.setText(eingabe);
            publisher = session.createPublisher(topic2);
            publisher.send(textMessage);
            publisher.close();


            //Get the response from Server
            subscriber = session.createSubscriber(topic1);
            TextMessage receiveMsg2 = (TextMessage) subscriber.receive();
            System.out.println(receiveMsg2.getText());


        }




        int choice = 0;
        System.out.println("For Adding input 1.\nFor get all Articles input 2.\nGet page of one article input 3.");
        do {
            Scanner sc = new Scanner(System.in);
            String input = "";
            choice = sc.nextInt();
            if(choice == 1) {
                System.out.println("Give the Name: ");
                Scanner scannName = new Scanner(System.in);
                String name = scannName.nextLine();

                System.out.println("Give the category: ");
                Scanner scanCat  =new Scanner(System.in);
                String caterogy = scanCat.nextLine();

                System.out.println("Give the Page: ");
                Scanner scanPage = new Scanner(System.in);
                int page = scanPage.nextInt();

                //Sent to Server the Infos for adding article to Newspapaer 1
                String sentMessage =eingabe+","+newspapaername+","+name+","+caterogy+","+page+",";

                //Send the Infos for Newspapaer
                publisher = session.createPublisher(topic2);
                textMessage = session.createTextMessage();
                textMessage.setText(sentMessage);
                publisher.send(textMessage);
                publisher.close();


                subscriber = session.createSubscriber(topic1);
                TextMessage receive = (TextMessage) subscriber.receive();
                System.out.println(receive.getText());

            }
            else if(choice == 2) {
            }
            else if(choice == 3) {
            }
        }while(choice==1 || choice==2 || choice==3);
        session.close();
        conn.close();
    }

}




