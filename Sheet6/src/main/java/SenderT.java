import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class SenderT {
   public static void main(String argv[]) throws Exception {

      Hashtable<String, String> properties = new Hashtable<String, String>();
	  properties.put(Context.INITIAL_CONTEXT_FACTORY, 
	                     "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
	  properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

      Context context = new InitialContext(properties);
   
      TopicConnectionFactory connFactory = 
	          (TopicConnectionFactory) context.lookup("ConnectionFactory");

      TopicConnection conn = connFactory.createTopicConnection();
      conn.start();
      TopicSession session = 
        conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
                    
      Topic topic = (Topic) context.lookup("dynamicTopics/topic1");
      
      TopicPublisher publisher = session.createPublisher(topic);
      TextMessage message = session.createTextMessage("Hello World!");
      System.out.println("Sending Message: "+ message.getText());
      publisher.publish(message);           
   
      session.close();
      conn.close();
   }
}