import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class ReceiverT {
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
      
	  TopicSubscriber subscriber = session.createSubscriber(topic);
	  TextMessage message = (TextMessage) subscriber.receive();
	  System.out.println("Message Received: " + message.getText());

      session.close();
      conn.close();
   }
}